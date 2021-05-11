package ru.job4j.iterator;
/**
 * класс описывает итератор итераторов
 * @author arvik
 * @version 1.0
 */

import java.util.Collections;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class FlatMap<T> implements Iterator<T> {
    /**
     * data - структура подобная массиву
     * cursor - указатель на итератор с которым ведется работа
     */
    private final Iterator<Iterator<T>> data;
    private Iterator<T> cursor = Collections.emptyIterator();

    public FlatMap(Iterator<Iterator<T>> data) {
        this.data = data;
    }

    /**
     *
     * @return курсор перемещается во внутренний итератор
     * курсор не указывает на следущий элемент итератора с которым ведется работа
     * И при этом курсор указывает на следующий внешний, берем этот элементы даты
     * если фалс, то cursor двигаем дальше
     * есть условных "два" итератора, если курсор одного не указывает на следущий элемент, т.е. закончился
     * но при этом показывает на следующий элемент второго итератора, то берем этот элемент второго итератора
     * иначе (если элемент первого итератора присутствует), двигаем курсор
     */
    @Override
    public boolean hasNext() {
        while (!cursor.hasNext() && data.hasNext()) {
            cursor = data.next();
        }
        return cursor.hasNext();
    }

    /**
     *
     * @return берем элемент на который указывал курсор
     */
    @Override
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return cursor.next();
    }
}
