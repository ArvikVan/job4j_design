package ru.job4j.io.encoding;
/**
 * класс описывает чат
 * @author arvik
 * @version 1.0
 */

import java.io.*;
import java.util.*;

public class ConsoleChat {
    private final String path;
    private final String botAnswers;
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private List<String> answers;

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    /**
     * запись в файл
     * @param dialog на входе что надо записать
     * @param path на входе куда надо записать
     *             стандартно try with resourses, открываем поток который не надо закрывать
     *             бежим по списку и записуем с переходом на след строку
     */
    public void write(List<String> dialog, String path) {
        try (PrintWriter printWriter = new PrintWriter(new FileWriter(path))) {
            dialog.forEach(printWriter::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * для чтения
     * открваем поток, читаем каждую строку потока,
     */
    public void read() {
        answers = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(botAnswers))) {
            bufferedReader.lines().forEach(answers::add);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * определяем ответы бота
     * @return на выходе рандомный выбор ответа
     */
    private String bot() {
            return answers.get(new Random().nextInt(answers.size()));
    }

    /**
     * создадим список в котором будет храниться переписка
     * сканер для ввовда с клавы
     * вводим с клавы
     * перерменную для флага
     * до тех пор пока мы не введи экв. закончить
     * выполняем цикл
     * если напечатали стоп, флаг false
     * если COUNTINUE флаг true
     * если это так(флаг true)б генерируем ответ, печатаем, записываем в список
     * вводим свои слова, записываем их, и так пока не закроем цикл
     * записываем все это в файл
     */
    public void run() {
        read();
        List<String> result = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Начинаем");
        String input = scanner.nextLine();
        result.add(input);
        boolean next = true;
        while (!input.equals(OUT)) {
            if (input.equals(STOP)) {
                next = false;
            }
            if (input.equals(CONTINUE)) {
                    next = true;
                }
            if (next) {
                String botAnswer = bot();
                System.out.println(botAnswer);
                result.add(botAnswer);
                System.out.println("Продолжим");
                }
            input = scanner.nextLine();
            result.add(input);
        }
        write(result, path);

    }

    public static void main(String[] args) {
        ConsoleChat cc =
               new ConsoleChat("./src/main/java/ru/job4j/io/encoding/path",
               "./src/main/java/ru/job4j/io/encoding/botAnswers");
        cc.run();
    }
}
