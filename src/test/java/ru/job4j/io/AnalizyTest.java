package ru.job4j.io;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.*;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class AnalizyTest {
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void serverLogsWithTempo() throws IOException {
        File source = folder.newFile("source.txt");
        File target = folder.newFile("target.txt");
        try (PrintWriter printWriter = new PrintWriter(source)) {
            printWriter.println("200 10:56:01");
            printWriter.println("500 10:57:01");
            printWriter.println("400 10:58:01");
            printWriter.println("500 10:59:01");
            printWriter.println("400 11:01:02");
            printWriter.println("200 11:02:02");
        }
        Analizy analizy = new Analizy();
        analizy.unavailable(source.getAbsolutePath(), target.getAbsolutePath());
        StringBuilder rsl = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new FileReader(target))) {
                in.lines().forEach(rsl::append);
        }
        assertThat(rsl.toString(), is("10:57:01; 11:02:02;"));
    }

}