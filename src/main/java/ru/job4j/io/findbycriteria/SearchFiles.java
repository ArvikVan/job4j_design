package ru.job4j.io.findbycriteria;
/**
 * класс описывает поиск по предикату, в нашем случае, по критерию
 * @author arvik
 * @version 1.0
 */

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class SearchFiles extends SimpleFileVisitor<Path> {
    /**
     * поля predicate и pathList
     * в первом условие, во втором пути
     * создаем конструктор что бы можно было использовать класс
     * и геттер для доступа к списку путей
     */
    private final Predicate<Path> predicate;
    private final List<Path> pathList = new ArrayList<>();

    public SearchFiles(Predicate<Path> predicate) {
        this.predicate = predicate;
    }

    public List<Path> getPathList() {
        return pathList;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        if (predicate.test(file)) {
            pathList.add(file);
        }
        return super.visitFile(file, attrs);
    }
}
