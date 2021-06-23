package ru.job4j.io.encoding;

import java.io.*;
import java.nio.charset.Charset;
import java.util.Collections;
import java.util.List;

/**
 * класс описывающий чтение в файл и запись новых данных в него
 * @author arvik
 * @version 1.0
 */
public class UsageEncoding {
    /**
     * чтение файла
     * @param path что надо читать
     * @return что прочитали
     * создаем объект стрбилдер
     * с помощью try with resourses  оборачиваем ридер в бафередридер
     * и по строкам добавляем все по чему пробежали в билдер
     * приводим к строке
     * Метод java.lang.StringBuilder.append () используется для добавления строкового
     * представления некоторого аргумента в последовательность.
     */
    public String readFile(String path) {
        StringBuilder builder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(path, Charset.forName("WINDOWS-1251")))) {
            br.lines().forEach(builder::append);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return builder.toString();
    }

    /**
     * запись данных в файл
     * @param path на входе файл
     * @param data на входе что надо записать
     *             открываем поток с использованием try with resourses
     * Params FileWriter:
     * fileName – String The system-dependent filename.
     * append – boolean if true, then data will be written to the end of the file rather than the beginning.
     *             записываем data в конец файла
     *
     */
    public void writeDataInFile(String path, List<String> data) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(path, Charset.forName("WINDOWS-1251"), true))) {
            data.forEach(pw::println);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String path = "./data/text";
        UsageEncoding encoding = new UsageEncoding();
        List<String> strings = List.of(
                "Новая строка 1",
                "Новая строка 2",
                "Новая строка 3",
                "Новая строка 4",
                "Новая строка 5"
        );
        for (String str : strings) {
            encoding.writeDataInFile(path, Collections.singletonList(str));
        }
        String s = encoding.readFile(path);
        System.out.println("Данные из файла:");
        System.out.println(s);
    }
}
