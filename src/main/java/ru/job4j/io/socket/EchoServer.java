package ru.job4j.io.socket;
/**
 * класс описывает общение клиент-сервер
 * @author arvik
 * @version 1.2
 * Добавлена валидация
 * Если клиент отправлять запрос http://localhost:9000/?msg=Bye нужно завершить работу сервера.
 * Ответить - Hello  http://localhost:9000/?msg=Hello
 * Завершить работу сервера. http://localhost:9000/?msg=Exit
 * Запрос с параметром What, должен вернуть ответ типа What. http://localhost:9000/?msg=What
 * Убрал цикл, потому что выводит все подряд, обернул в if
 * Добавил валидацию согласно условию
 *
**/
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
    public static void main(String[] args) throws IOException {
        /**
         * try (ServerSocket server = new ServerSocket(9000))
         * ServerSocket создает сервер. Чтобы клиент мог узнать,
         * где находится сервер ему нужен адрес и порт. По аналогии с людьми мы обращаемся по имени,
         * чтобы начать разговор.
         * 9000 - это порт. По умолчанию адрес будет localhost.
         */
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                // server.acсept - ожидает, когда к нему обратиться клиент. Программа переходит в режим ожидания.
                Socket socket = server.accept();
                //Когда клиент обратился к серверу программа получает входной поток и может
                // отправить данные в выходной поток.
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    //В ответ мы записываем строчку.
                    out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    String str = in.readLine();
                    if (str != null && !str.isEmpty()) {
                        System.out.println(str);
                        if (str.contains("msg=Hello")) {
                            out.write("Hello, dear friend.\r\n".getBytes());
                        } else if (str.contains("msg=Exit")) {
                            out.write("The server is closed.\r\n".getBytes());
                            server.close();
                        } else {
                            out.write("What.\r\n".getBytes());
                        }
                    }
                }
            }
        }
    }
}
