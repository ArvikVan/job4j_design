package ru.job4j.collection.set;
/**
 * класс описывает коллекцию Set
 * @author arvik
 * @version 1.0
 */

import ru.job4j.collection.SimpleArray;
import java.util.Iterator;
import java.util.Objects;

public class SimpleSet<T> implements Set<T> {
    private SimpleArray<T> simpleArray = new SimpleArray<>();

    /**
     * описываем метод добавления в коллекцию
     * @param value на вход значение которое пихаем в коллекцию
     * @return на выходе труе если добавили
     * проверяем уникальность в цсловии
     */
    @Override
    public boolean add(T value) {
        if (!contains(value)) {
            simpleArray.add(value);
            return true;
        }
        return false;
    }

    /**
     * описываем метод сравнения
     * @param value значение которое сравнивать будем
     * @return на выходе труе если совпало
     * создаем булево переменную
     * циклом бежим по коллекции и в условии сравниваем объекты с помощью
     * equals(Object a, Object b), если труе то , result = true; и на выход
     */
    @Override
    public boolean contains(T value) {
        boolean result = false;
        for (T t : simpleArray) {
            if (Objects.equals(t, value)) {
                result = true;
                break;
            }
        }
        return result;
    }

    @Override
    public Iterator<T> iterator() {
        return simpleArray.iterator();
    }
}
