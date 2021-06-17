package ru.job4j.io;
/**
 * класс описывает работу с директориями файлов, класс в котором проводится манипуляция
 * @author arvik
 * @version 1.1
 * добавлена валидация, изменена конфигурация - добавлены аргументы
 */

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

public class Search {
    /**
     * точка входа
     * @param args собственно
     * @throws IOException во избежание  ошибок
     * полчаем объект типа Path
     * поиск по предикату, выбираем файлы которые заканчиваются на js
     */
    public static void main(String[] args) throws IOException {
        /**
         * производим валидацию, должно быть два аргумента, если меньше двух то исключение
         * первый аргумент путь, второй то расширение которое надо найти
         * аргументы указаны в Edite Configuration через пробел
         */
        if (args.length < 2) {
            throw new IllegalArgumentException("Need to set two parameters");
        }
        Path start = Paths.get(args[0]);
        search(start, p -> p.toFile().getName().endsWith(args[1])).forEach(System.out::println);
    }

    /**
     * поискатель
     * @param root на входе корень
     * @param condition на входе условие
     * @return на выходе список путей
     * @throws IOException ловим ошибки
     * Создаем объект searcher и пихаем в него условие
     * указываем границы в которых нужен поиск Files.walkFileTree(root, searcher);
     * возвращаем списко путей return searcher.getPathList();
     */
    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPathList();
    }
}
