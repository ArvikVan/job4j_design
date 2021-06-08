package ru.job4j.io;
/**
 * класс описывает запис в файл, в данном случае таблицы умножения
 * @author arvik
 * @version 1.0
 */

import java.io.FileOutputStream;

public class Matrix {
    public void multiple(int size) {
        try (FileOutputStream fileOutputStream = new FileOutputStream("multiTable.txt")) {
            for (int i = 1; i <= size; i++) {
                for (int j = 1; j <= size; j++) {
                    String result = i * j + "\t";
                    fileOutputStream.write(result.getBytes()); //результат
                } fileOutputStream.write(System.lineSeparator().getBytes()); // перевод каретки
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Matrix().multiple(10);
    }
}
