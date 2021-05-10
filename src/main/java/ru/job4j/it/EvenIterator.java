package ru.job4j.it;
/**
 * класс описывает итератор четных чисел
 * @author arvik
 * @version 1.0
 */

import java.util.Iterator;
import java.util.NoSuchElementException;

public class EvenIterator implements Iterator {
    private final int[] numbers;
    private int point = 0;


    public EvenIterator(int[] numbers) {
        this.numbers = numbers;
    }

    /**
     * Указатель
     * @return на выходе указываем на след. элемент
     * до тех пор пока выыполняется условие цикла, работаем
     * numbers[point] % 2 == 0 уловие при котором получаем четные числа, выходим из цикла и сдвигаем указатель
     */
    @Override
    public boolean hasNext() {
        boolean result = false;
        while (point < numbers.length) {
            if (numbers[point] % 2 == 0) {
                result = true;
                break;
            }
            point++;
        }
        return result;
    }

    /**
     * сдвигаем указатель
     * @return на выходе следующий элемент (сдвигаем указатель)
     */
    @Override
    public Object next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return numbers[point++];
    }
}
