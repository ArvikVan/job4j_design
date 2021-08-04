package ru.job4j.io.scanner;

import ru.job4j.io.ArgsName;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.IntStream;

/**
 * @author arvik
 * @version 1.1
 * Задача класса прочитать данные из CSV файла и вывести их.
 * 1.1 Вместо \n надо использовать System.lineSeparator().
 * 1.1 Что не делать проверки можно поступить проще: for (int index : row)
 * 1.1 Вместо FileWriter создали нужный PrintStream
 * try (PrintStream out = new PrintStream(... ? new FileOutputStream(...) : System.out)) {
 *
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
             PrintStream fileWriter = new PrintStream(!out.equals("stdout")
                     ? new FileOutputStream("src/main/java/ru/job4j/io/scanner/outexample.csv") : System.out) //1.1
            ) {
            String[] collName = scanner.next().split(delimetr);

           List<Integer> row = new ArrayList<>();
            for (int i = 0; i < collName.length; i++) {
                if (filters.contains(collName[i])) {
                    row.add(i);
                }
            }
            fileWriter.println(filters + System.lineSeparator()); //1.1
            while (scanner.hasNext()) {
                String tmp = "";
                String[] coll = scanner.next().split(delimetr);
                for (int integer : row) { //1.1
                    tmp = tmp.concat(tmp.isBlank() ? "" : "; ") + coll[integer];
                }
                //1.1
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
