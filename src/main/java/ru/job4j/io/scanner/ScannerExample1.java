package ru.job4j.io.scanner;

import java.io.CharArrayReader;
import java.util.Scanner;

/**
 * @author arvik
 * @version 1.0
 * Пусть надо из потока данных вычленить только числа. Для этого можно воспользоваться Scanner следующим образом
 *
 */
public class ScannerExample1 {
    public static void main(String[] args) {
        var ls = System.lineSeparator();
        var data = String.join(ls, "1 2 3", "4 5 6 ", "7 8 9");
        var scanner = new Scanner(new CharArrayReader(data.toCharArray()));

        while (scanner.hasNextInt()) {
            System.out.print(scanner.nextInt());
            System.out.print(" ");
        }
    }
}
