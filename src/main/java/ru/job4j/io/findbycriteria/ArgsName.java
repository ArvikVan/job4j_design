package ru.job4j.io.findbycriteria;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * класс описывает валидацию входных параметров
 */
public class ArgsName {
    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        return values.get(key);
    }
    /**
     *
     * @param args на вход массив параметров
     *             проверяем, если параметров не 4 то ексепшн
     *             бежим по args и делим по = и записываем в массив
     *             если в разделенном меньше пары то ексепшн
     *             результат пишем в мапу, причем дефис удаляем посредством substring()
     */
    public void parse(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("....args.length == 0....");
        }
        for (String arg : args) {
            String[] temp = arg.split("=");
            if (temp.length < 2) {
                throw new IllegalArgumentException("....temp.length < 2.....");
            }
            values.put(temp[0].substring(1), temp[1]);
        }
    }

    /**
     *
     * @param args на входе начальный массив
     * @return на выходе проверенный и разделенный
     */
    public static ArgsName of(String[] args) {
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }
}
