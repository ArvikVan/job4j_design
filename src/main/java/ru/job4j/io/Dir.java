package ru.job4j.io;
/**
 * класс описывает работу с директориями
 * @author arvik
 * @version 1.1
 * file.getName(), file.length(), file.getTotalSpace()
 * без String.format
 */

import java.io.File;

public class Dir {
    public static void main(String[] args) {
            File file = new File("/home/arvik/IdeaProjects");
            if (!file.exists()) {
                throw new IllegalArgumentException(String.format("Not exist %s", file.getAbsoluteFile()));
            }
            if (!file.isDirectory()) {
                throw new IllegalArgumentException(String.format("Not directory %s", file.getAbsoluteFile()));
            }
            System.out.printf("The fileName: %s, and the fileLength: %s%n", file.getName(), file.length());
    }
}
