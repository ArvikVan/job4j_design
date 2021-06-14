package ru.job4j.io;
/**
 * класс описывает выбор строки содержащей определенное условие
 * @author arvik
 * @version 1.1
 * добавлен метод save() в котором производим запись в файл
 */

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class LogFilter {
    /**
     *
     * @param file на вxоде получим строку
     * @return на выходе результат фильтрации
     * создаем аррайлист для хранения полученного результата
     * используем конструкцию try-with-resources
     * открываем поток (декоратор) оборачиваем new BufferedReader(new FileReader())
     * создаем строковую переменную в которую потом будем записывать значение
     * до тех пор пока это значение не равно null, т.е. пока еще есть символы
     * делим строку на символы, делитель пробел, и поместим все в массив строк
     * если предпоследний элемент массива 404, то добавляем его в наш аррйлист
     */
    public static List<String> filter(String file) {
        List<String> choosenElement = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = in.readLine()) != null) {
                String[] str = line.split(" ");
                if ("404".equals(str[str.length - 2])) {
                    choosenElement.add(line);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return choosenElement;
    }

    /**
     *
     * @param log на входе список строк полученный в фильтре
     * @param file на входе строки которые надо записать в файл
     */
    public static void save(List<String> log, String file) {
        try (PrintWriter out = new PrintWriter(new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream(file))))) {
                    out.write(String.valueOf(log));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        List<String> log = filter("log.txt");
        System.out.println(log);
        save(log, "404.txt");
    }

}
