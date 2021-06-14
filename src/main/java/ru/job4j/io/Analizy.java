package ru.job4j.io;

import java.io.*;

/**
 * класс описывает преобразование одного файла в другой
 * @author arvik
 * @version 1.0
 */

public class Analizy {
    /**
     *
     * @param source путь к файлу лога
     * @param target имя путь к файлу результата анализа
     * создаем переменную, в которую будем писать потом период
     * используем конструкцию try-with-resources
     * открываем поток (декоратор) оборачиваем new BufferedReader(new FileReader())
     * открываем поток на запись в файл
     * бежим по чтению и бьем по пробелу, и записываем в массив
     *               первым будет статус, вторым время
     * Сервер не работал, если status = 400 или 500.
     * записываем в файл и закрываем поток
     */
    public void unavailable(String source, String target) {
        String startTime = null;
        try (BufferedReader reader = new BufferedReader(new FileReader(source));
            PrintWriter writer = new PrintWriter(new BufferedOutputStream(new FileOutputStream(target)))) {
             for (String s = reader.readLine(); s != null; s = reader.readLine()) {
                String[] arr = s.split(" ");
                if ((arr[0].equals("400") || arr[0].equals("500")) && startTime == null) {
                    startTime = arr[1];
                } else if ((arr[0].equals("200") || arr[0].equals("300")) && startTime != null) {
                    writer.printf("%s%n", startTime + "; " + arr[1] + ";");
                }
            }
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Analizy analizy = new Analizy();
        analizy.unavailable("source.txt", "target.txt");
    }
}
