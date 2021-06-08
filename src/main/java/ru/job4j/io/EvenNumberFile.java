package ru.job4j.io;

import java.io.FileInputStream;

/**
 * класс описывает чтение из файла
 * @author arvik
 * @version 1.0
 * В классе нужно прочитать файл even.txt.
 * Для каждого числа проверить является ли оно четным числом.
 * Ответ вывести на консоль.
 */
public class EvenNumberFile {
    public static void main(String[] args) {
        try (FileInputStream fileInputStream = new FileInputStream("even.txt")) {
            StringBuilder stringBuilder = new StringBuilder();
            int result;
            //считываем каждый отдельный байт в переменную result
            //Когда в потоке больше нет данных для чтения, метод возвращает число -1.
            //Затем каждый считанный байт конвертируется в объект типа char и выводится на консоль.
            while ((result = fileInputStream.read()) != -1) {
                stringBuilder.append((char) result);
            }
            String[] lines = stringBuilder.toString().split(System.lineSeparator());
            for (String line : lines) {
                System.out.println("Четное число: " + line + " " + (Integer.parseInt(line) % 2 == 0));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
