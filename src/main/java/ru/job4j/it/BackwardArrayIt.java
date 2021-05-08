package ru.job4j.it;
/**
 * Класс описывает итератор массива, в данном случае в обратном порядке
 * @author arvik
 * @version 1.0
 */

import java.util.Iterator;
import java.util.NoSuchElementException;

public class BackwardArrayIt implements Iterator<Integer> {
    private final int[] data;
    private int point = 0;

    public BackwardArrayIt(int[] data) {
        this.data = data;
    }

    /**
     * hasNext указывает на наличие следующего элемента
     * @return  на выходе true, если пойнт меньше длины массива
     */
    @Override
    public boolean hasNext() {
        return point < data.length;
    }

    /**
     * next возвращает элемент на который указывал hasNext
     * @return идем с последнего элемента массива, с каждым разом увеличивая point
     * и тем самым приближаясь к нулю
     */
    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return data[data.length - 1 - point++];
    }
}
