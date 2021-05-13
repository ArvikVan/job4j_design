package ru.job4j.generics;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class IteratorForSimpleArray<T> implements Iterator<T> {
    private final T[] array;
    private int point = 0;

    public IteratorForSimpleArray(T[] array) {
        this.array = array;
    }

    @Override
    public boolean hasNext() {
        return point < array.length;
    }

    @Override
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return array[point++];
    }
}
