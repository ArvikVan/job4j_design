package ru.job4j.io;
/**
 * класс описывает логгирование системы
 * @author arvik
 * @version 1.2
 * заменены импорты и подключена в поме библиотека slf4j
 * private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());
 * Создайте 8 переменных с примитивным типом. Все типы должны быть разными.
 * Выведите переменные на консоль через логгер.
 */

import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {
    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        BasicConfigurator.configure();
        int i = Integer.MAX_VALUE;
        byte b = Byte.MAX_VALUE;
        long l = Long.MAX_VALUE;
        short s = Short.MAX_VALUE;
        char c = 1010;
        float f = Float.MAX_VALUE;
        double d = Double.MAX_VALUE;
        boolean bl = true;
        LOG.debug("\n" + "Value of int: {}, Value of byte: {}, Value of long: {}, "
                +  "Value of short: {}, Value of char: {}, Value of float: {}, "
                + "Value of double: {}, Value of boolean: {},",
                i + "\n", b + "\n", l + "\n", s + "\n", c + "\n", f + "\n", d + "\n", bl);
    }
}
