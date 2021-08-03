package ru.job4j.io.scanner;

import ru.job4j.io.ArgsName;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.IntStream;

/**
 * @author arvik
 * @version 1.0
 * Задача класса прочитать данные из CSV файла и вывести их.
 * src/main/java/ru/job4j/io/scanner/example.csv
 */
public class CSVReader {
    private final Path path;
    private final String delimetr;
    private final String out;
    private final List<String> filters;

    public CSVReader(Path path, String delimetr, String out, String[] filters) {
        this.path = path;
        this.delimetr = delimetr;
        this.out = out;
        this.filters = List.of(filters);
    }

    /**
     * метод описывает считываение из файла и запись в файл
     * в конструкции try with resourses создадим Scanner scanner и FileWriter fileWriter
     * один для чтения, другой для записи
     * создаем массив collName в который пихаем наш прочтеный файл
     * создаем лист row в который пихаем индекс отфильтрованных столбцов (из аргументов)
     * в цикле бежим по сканеру
     * создадим массив coll для хранения результата фильтрации по столбцам
     * создадим строку в которую будем складывать результат
     * далее валидация на выбор вывода, либо консоль либо файл
     *
     */
    public void read() {
        try (Scanner scanner = new Scanner(path).useDelimiter(System.lineSeparator());
            FileWriter fileWriter = new FileWriter("src/main/java/ru/job4j/io/scanner/outexample.csv", false);) {
            String[] collName = scanner.next().split(delimetr);

           List<Integer> row = new ArrayList<>();
            for (int i = 0; i < collName.length; i++) {
                if (filters.contains(collName[i])) {
                    row.add(i);
                }
            }
            fileWriter.write(filters + "\n");
            while (scanner.hasNext()) {
                String tmp = "";
                String[] coll = scanner.next().split(delimetr);
                for (int i = 0; i < coll.length; i++) {
                    if (row.contains(i)) {
                        tmp = tmp.concat(tmp.isBlank() ? "" : "; ") + coll[i];
                    }
                }
                if (!out.equals("stdout")) {
                    fileWriter.write(tmp + "\n");
                } else {
                    System.out.println(tmp);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        ArgsName argsName = ArgsName.of(args);
        Path path = Path.of(argsName.get("path"));
        if (args.length != 4 || !path.toFile().isFile()) {
            throw new IllegalArgumentException("Invalid path: " + path);
        }
        CSVReader csvReader = new CSVReader(path,
                argsName.get("delimetr"),
                argsName.get("out"),
                argsName.get("filters").split(","));

        csvReader.read();
    }
}
