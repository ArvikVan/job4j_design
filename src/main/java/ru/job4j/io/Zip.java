package ru.job4j.io;
/**
 * класс описывает архивацию папки
 * @author arvik
 * @version 1.0
 */

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {
    /**
     * для архивации всего на свете
     * @param sources на входе все что надо архивнуть
     * @param target во что надо это превратить
     *               Для архивации использовать классы ZipOutputStream
     *               используем конструкцию try-with-resources
     *               запускаем потоки
     *  Исходный поток ввода - это файл FileOutputStream. Он записывает данные по байтам.
     *               Блокирует всю программу, пока запись не закончится. Это плохо.
     *  Первая обертка - это BufferedOutputStream. Это буфер, который собираем переданные в него байты.
     *               Аккумулирует их и постепенно отдает их в FilOutputStream. В этом случае программа не блокируется
     *               до тех пока в буфере есть место.
     *  Вторая обертка над буфером - это ZipOutputStream.
     *               бежим по всему что есть и архивируем это, согласно примеру packSingleFile
     *               Обязательно добавляем в конфигурацию аргументы, а моем случае
     *               -d=/home/arvik/IdeaProjects/job4j_design -e=class -o=project.zip через пробел
     */
    public void packFiles(List<Path> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (Path path : sources) {
                zip.putNextEntry(new ZipEntry(path.toFile().getPath()));
                try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(path.toFile()))) {
                    zip.write(out.readAllBytes());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(out.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param args на входе параметры
     * @throws IOException ловим исключения
     * НЕ ЗАБЫТЬ прописать параметры запуска в project.jar
     */
    public static void main(String[] args) throws IOException {
        ArgsName argsName = ArgsName.of(args);
        List<Path> result = Search.search(Paths.get(argsName.get("d")),
                p -> !p.toFile().getName().endsWith(argsName.get("e")));
        new Zip().packFiles(result, new File(argsName.get("o")));
        System.out.println(argsName.get("d"));
        System.out.println(argsName.get("e"));
        System.out.println(argsName.get("o"));
    }
}
