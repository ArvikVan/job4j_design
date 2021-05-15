package ru.job4j.generics;
/**
 * класс описывает arrayList
 * @author arvik
 * @version 1.0
 */

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleArray<T> implements Iterable<T> {
    private final T[] models;
    private int length = 0;

    /**
     * конструктор
     */
    public SimpleArray(int size) {
        this.models = (T[]) new Object[size];
    }

    /**
     *
     * @param model на вход элемент который надо добавить
     * models[length++] = model; присваиваем значение model и увеличиваем значение индекса
     */
    public void add(T model) {
        models[length++] = model;
    }

    /**
     *
     * @param index индекс элемента который необходимо заменить
     * @param model элемент на который будем менять
     * Objects.checkIndex(index, length); - Проверяет, находится ли индекс в
     *              пределах диапазона от 0 (включительно) до длины (исключая).
     * models[index] = model; присваиваем ячейке с индексом, значение model
     */
    public void set(int index, T model) {
        Objects.checkIndex(index, length);
        models[index] = model;
    }

    /**
     *
     * @param index на входе индекс элемента который надо удалить
     * Objects.checkIndex(index, length); проверяем, находится ли индекс в пределах
     *              диапазона
     * System.arraycopy(models, index + 1, models, index, length - index - 1);
     *              создаем копию массива
     *              - models что копируем
     *              - index + 1 с какого элемента копируем старый массив
     *              - models куда копируем
     *              - index с какого элемента копируем в новый массив
     *              - length - index - 1 сколько хотим скопировать
     * length--; уменьшаем размер массива
     */
    public void remove(int index) {
        Objects.checkIndex(index, length);
        System.arraycopy(models, index + 1, models, index, length - index - 1);
        length--;
    }

    /**
     *
     * @param index на вход индекс элемента который надо вернуть
     * @return на выходе ячейка с индексом того что нужно get
     * Objects.checkIndex(index, length); - Проверяет, находится ли индекс в
     *              пределах диапазона от 0 (включительно) до длины (исключая).
     */
    public T get(int index) {
        Objects.checkIndex(index, length);
        return models[index];
    }

    @Override
    public Iterator<T> iterator() {
        return new Itr();
    }

    private class Itr implements Iterator<T> {
        int cursor = 0;

        @Override
        public boolean hasNext() {
            return cursor < length;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return (T) models[cursor++];
        }
    }
}