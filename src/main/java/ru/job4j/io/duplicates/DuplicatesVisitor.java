package ru.job4j.io.duplicates;
/**
 * класс описывает просмотр директории и поиск дубликатов в ней
 * @author arvik
 * @version 1.0
 */

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashSet;
import java.util.Set;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    /**
     * хранилище для уникальных файлов
     * запихнем в нее нашу модель данных
     */
    Set<FileProperty> filePropertySet = new HashSet<>();

    /**
     * переопределяем метод
     * @param file на входе путь файла
     * @param attrs ередаем атрибут BasicFileAttributes
     * @return продолжаем обход дерева
     * CONTINUE – Indicates that the file walking should continue.
     * @throws IOException внимание на ошибки
     * создаем объект нашей модели данных, и указываем длину и имя файла
     * если структура данных содержит этот файл, то в консоль пишем путь
     * иначе в хранилище
     */
    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty fileProperty = new FileProperty(file.toFile().length(), file.toFile().getName());
        if (filePropertySet.contains(fileProperty)) {
            System.out.println("duble: - " + file.toAbsolutePath());
        } else {
            filePropertySet.add(fileProperty);
        }
        return FileVisitResult.CONTINUE;
    }
}
