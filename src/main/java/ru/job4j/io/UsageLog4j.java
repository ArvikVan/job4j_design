package ru.job4j.io;
/**
 * класс описывает логгирование системы
 * @author arvik
 * @version 1.1
 * заменены импорты и подключена в поме библиотека slf4j
 * private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());
 */

import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {
    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        BasicConfigurator.configure();
        LOG.trace("trace message");
        LOG.debug("debug message");
        LOG.info("info message");
        LOG.warn("warn message");
        LOG.error("error message");
    }
}
