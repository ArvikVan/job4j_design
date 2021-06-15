package ru.job4j.io;
/**
 * класс описывает работу с директориями
 * @author arvik
 * @version 1.0
 * file.getName(), file.length(), file.getTotalSpace()
 */

import java.io.File;

public class Dir {
    public static void main(String[] args) {
            File file = new File("/home/arv/IdeaProjects");
            if (!file.exists()) {
                throw new IllegalArgumentException(String.format("Not exist %s", file.getAbsoluteFile()));
            }
            if (!file.isDirectory()) {
                throw new IllegalArgumentException(String.format("Not directory %s", file.getAbsoluteFile()));
            }
            System.out.println(String.format
                    ("The fileName: %s, and the fileLength: %s", file.getName(), file.length()));
    }
}
