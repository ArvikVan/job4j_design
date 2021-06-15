package ru.job4j.io;
/**
 * класс описывает поиск, используем только visitFile
 * @author arvik
 * @version 1.0
 */
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
public class SearchFiles implements FileVisitor<Path> {
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
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
        return FileVisitResult.CONTINUE;
    }

    /**
     * работаем только с этим методом
     * @param file на входе путь файал
     * @param attrs передаем атрибут BasicFileAttributes
     * @return указывает, что перемещение файла должно продолжаться.
     * @throws IOException ловим ошибки
     * если условие выполняется, то добавляем путь в список
     */
    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        if (predicate.test(file)) {
            pathList.add(file);
        }
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
        return FileVisitResult.CONTINUE;
    }
}
