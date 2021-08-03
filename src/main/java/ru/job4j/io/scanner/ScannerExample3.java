package ru.job4j.io.scanner;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

/**
 * @author arvik
 * @version 1.0
 * Еще одной интересной возможностью Scanner является возможность задать систему счисления при чтении чисел.
 * Например, можно прочитать числа в шестнадцатеричном виде и вывести в десятичном таким образом:
 */
public class ScannerExample3 {
    public static void main(String[] args) throws IOException {
        var data = "A 1B FF 110";
        var file = File.createTempFile("data", null);
        try (BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(file))) {
            out.write(data.getBytes());
        }
        try (var scanner = new Scanner(file).useRadix(16)) {
            while (scanner.hasNextInt()) {
                System.out.print(scanner.nextInt());
                System.out.print(" ");
            }
        }
    }
}
