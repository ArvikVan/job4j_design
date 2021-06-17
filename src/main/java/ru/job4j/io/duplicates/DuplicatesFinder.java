package ru.job4j.io.duplicates;
/**
 * точка входа
 * @author arvik
 * @version 1.0
 */

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class DuplicatesFinder {
    public static void main(String[] args) throws IOException {
        /**
         * обходим древо walkFileTree(Path, FileVisitor)
         */
        Files.walkFileTree(Path.of("./"), new DuplicatesVisitor());
    }
}
