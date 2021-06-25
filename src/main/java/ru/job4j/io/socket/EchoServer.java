package ru.job4j.io.socket;
/**
 * класс описывает общение клиент-сервер
 * @author arvik
 * @version 1.1
 * Добавлена валидация
 *  Если клиент отправлять запрос http://localhost:9000/?msg=Bye нужно завершить работу сервера.
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
                    out.write("HTTP/1.1 200 OK\r\n\"".getBytes());
                    //В программе читается весь входной поток.
                    for (String str = in.readLine(); str != null && !str.isEmpty(); str = in.readLine()) {
                        if (str.contains("msg=Bye")) {
                            server.close();
                        }
                        System.out.println(str);
                    }
                }
            }
        }
    }
}
