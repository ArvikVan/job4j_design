package ru.job4j.io;
/**
 * класс описывает запись в файл
 * @author arvik
 * @version 1.0
 */

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.PrintWriter;

public class ResultFile {
    public static void main(String[] args) {
        /*try (FileOutputStream out = new FileOutputStream("result.txt")) {
            out.write("Hello, world!".getBytes());*/
        try (PrintWriter out = new PrintWriter(new BufferedOutputStream(new FileOutputStream("result.txt")))) {
            out.write("Hello, world!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
