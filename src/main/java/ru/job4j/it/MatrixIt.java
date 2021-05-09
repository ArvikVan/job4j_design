package ru.job4j.it;
/**
 * класс описывает итератор двумерного массива
 * @author arvik
 * @version 1.0
 */

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MatrixIt implements Iterator<Integer> {
    private final int[][] data;
    private int row = 0;
    private int column = 0;

    public MatrixIt(int[][] data) {
        this.data = data;
    }

    /**
     * Указатель
     * @return до тех пор пока указатель row будет меньше длинны строки это указывает на то
     * что это еще не конец и есть куда передвигаться
     * если ячейка пуста (длинна строки !равна указателю column) перескакиваем дальше
     * иначе передвигаем указатель в следующую ячейку строки row++ и column = 0
     */
    @Override
    public boolean hasNext() {
        while (row < data.length) {
            if (data[row].length != column) {
                return true;
            } else {
                row++;
                column = 0;
            }
        }
        return false;
    }

    /**
     * Получатель
     * @return на выходе элемент, в данном случае координаты ячейки на которую указал
     * hasNext()
     */
    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return data[row][column++];
    }
}
