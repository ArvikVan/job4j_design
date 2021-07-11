package ru.job4j.io.findbycriteria;

import javax.management.monitor.StringMonitor;
import java.awt.geom.IllegalPathStateException;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Pattern;

public class SearchByCriteria {

    private static void write(List<Path> paths, String rsl) throws FileNotFoundException {
        try (PrintWriter printWriter = new PrintWriter(rsl)) {
            paths.stream().forEach(path -> printWriter.println(path.toFile().getAbsolutePath()));
        }
    }

    /**
     *
     * @param root на входе путь
     * @param name на входе имя
     * @param type на входе тип маска/имя полностью/по содержанию
     * @return на выходе путбь
     * @throws IOException
     */
    private static List<Path> search(Path root, String name, String type) throws IOException {
        Predicate<Path> criteria;
        if (type.equals("name")) {
            criteria = x -> x.toFile().getName().equals(name);
        } else if (type.equals("mask")) {
            criteria = x -> x.toFile().getName().contains(name);
        } else {
            criteria = x -> x.toFile().getName().matches(name);
        }
        SearchFiles searchFiles = new SearchFiles(criteria);
        Files.walkFileTree(root, searchFiles);
        return searchFiles.getPathList();
    }



    public static void main(String[] args) throws IOException {
        ArgsName argsName = ArgsName.of(args);
        String dir = argsName.get("d");
        String name = argsName.get("n");
        String type = argsName.get("t");
        String rsl = argsName.get("o");

        if (dir == null || name == null || type == null || rsl == null) {
            throw new IllegalArgumentException("The input does not match to template");
        }
        if (!type.equals("mask") && !type.equals("name") && !type.equals("regex")) {
            throw new IllegalArgumentException("Wrong search type argument");
        }
        if (!Files.exists(Paths.get(dir))) {
            throw new FileNotFoundException("The source directory does not exist");
        }
        if (!Files.exists(Paths.get(rsl))) {
            Files.createFile(Paths.get(rsl));
        }
        write(search(Paths.get(dir), name, type), rsl);
    }
}
