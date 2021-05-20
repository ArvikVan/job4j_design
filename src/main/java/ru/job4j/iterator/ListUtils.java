package ru.job4j.iterator;
/**
 * класс описывает ListOperator
 * @author arvik
 * @version 1.0
 */

import java.util.List;
import java.util.ListIterator;
import java.util.Objects;
import java.util.function.Predicate;

public class ListUtils {
    /**
     * Добавляем элемент после указанного индекса
     * @param list список на вход
     * @param index индекс на вход
     * @param value значение на вход
     * @param <T> же
     *           проверяем находится ли индекс в диапазоне
     *           вызываем листитератор
     *           до тех пор пока есть следующий элемент цикл выполняется
     *           0-previosIndex-1-nextIndex
     *           если следующий индекс == index
     *           добавляем наше value
     */
    public static <T> void addBefore(List<T> list, int index, T value) {
        Objects.checkIndex(index, list.size());
        ListIterator<T> i = list.listIterator();
        while (i.hasNext()) {
            if (i.nextIndex() == index) {
                i.add(value);
                break;
            }
            i.next();
        }
    }

    /**
     * Добавляем элемент после указанного индекса
     * @param list список на вход
     * @param index индекс на вход
     * @param value значение которое надо добавить
     * @param <T> тип
     *           проверяем и создаем листератор
     *           до тех пор пока есть следующий элемент цикл выполняется
     *           если следущий индекс == index
     *           перелетаем на след.ступень и добавляем элемент value
     *           можно было использовать previosIndex(), если бы не надо было доавить в конец
     */
    public static <T> void addAfter(List<T> list, int index, T value) {
        Objects.checkIndex(index, list.size());
        ListIterator<T> listIterator = list.listIterator();
        while (listIterator.hasNext()) {
            if (listIterator.nextIndex() == index) {
                listIterator.next();
                listIterator.add(value);
                break;
            }
            listIterator.next();
        }
    }

    /**
     * Удаляем элемент после условия
     * @param list список
     * @param filter условие
     * @param <T> тип
     *           пока hasNext() видит впереди элементы работаем
     *           применяем фильтр и удаляем неугодных
     */
    public static <T> void removeIf(List<T> list, Predicate<T> filter) {
        ListIterator<T> i = list.listIterator();
        while (i.hasNext()) {
            if (filter.test(i.next())) {
                i.remove();
            }
        }
    }

    /**
     * заменяет все элементы, которые удовлетворяют предикату;
     * @param list список
     * @param filter условие
     * @param value значение на которое меняем
     * @param <T> тип
     *           пока hasNext() видит впереди элементы работаем
     *           применяем фильтр и меняем неугодных
     */
    public static <T> void replaceIf(List<T> list, Predicate<T> filter, T value) {
        ListIterator<T> i = list.listIterator();
        while (i.hasNext()) {
            if (filter.test(i.next())) {
                i.set(value);
            }
        }
    }

    /**
     * удаляет из списка те элементы, которые есть в elements.
     * @param list список
     * @param elements список который надо удалить
     * @param <T> тип
     *           пока hasNext() видит впереди элементы работаем
     *           идем по листу и удалем все что похоже на elements
     */
    public static <T> void removeAll(List<T> list, List<T> elements) {
        ListIterator<T> i = list.listIterator();
        while (i.hasNext()) {
            if (elements.contains(i.next())) {
                i.remove();
            }
        }
    }

}
