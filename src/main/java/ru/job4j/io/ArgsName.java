package ru.job4j.io;
/**
 * класс описывает разбитие массива параметров на пары
 * @author arvik
 * @version 1.0
 */

import java.util.HashMap;
import java.util.Map;

public class ArgsName {
    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        return values.get(key);
    }

    /**
     *
     * @param args на входе массив параметров, который необходимо разбить на пары
     *             проверяем длину этого массива, а также кол-во полученных параметров
     *            для этого бежим по этому массивуи разбиваем каждый параметр по знаку "="
     *             и кладем в нашу мапу values ( [0] и [1]) при этом с помощью метода
     *             substring(1) избавляемся от первого дефиса
     */
    private void parse(String[] args) {
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

    public static ArgsName of(String[] args) {
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[] {"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[] {"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}
