package ru.job4j.collection;
/**
 * класс описывает динамический массив
 * работа с API documentation
 * https://docs.oracle.com/en/java/javase/14/docs/api/java.base/java/util/ArrayList.html#%3Cinit%3E()
 * и с описанием класса ArrayList в IDE
 */

import java.util.*;

public class SimpleArray<T> implements Iterable<T> {
    /**
     * private Object[] container; внутренний массив
     * private int modCount = 0; счетчик изменений
     * private int size; размер массива
     * DEFAULT_CAPACITY размер для массива по умолчанию
     * EMPTY_ELEMENTDATA Общий экземпляр пустого массива, используемый для пустых экземпляров
     */
    private Object[] container;
    private int modCount;
    private static final int DEFAULT_CAPACITY = 10;
    private int size;
    private static final Object[] EMPTY_ELEMENTDATA = {};

    /**
     * конструктор заданного размера
     * @param initialCapacity на входе инициализация вместимости массива
     *                        если больше 0, то создается массив типа Object
     *                        вместимостью initialCapacity.
     * Если равен 0, то берется пустой массив
     *                        иначе Illegal Capacity:
     */
    public SimpleArray(int initialCapacity) {
        if (initialCapacity > 0) {
            this.container = new Object[initialCapacity];
        } else if (initialCapacity == 0) {
            this.container = EMPTY_ELEMENTDATA;
        } else {
            throw new IllegalArgumentException("Illegal Capacity: " + initialCapacity);
        }
    }

    /**
     * конструктор по умолчанию, ращзмером 10
     */
    public SimpleArray() {
        this(DEFAULT_CAPACITY);
    }

    /**
     *
     * @param index индекс элемента
     * @return на выходе элемент в указанном положении списка
     */
    public T get(int index) {
        Objects.checkIndex(index, size);
        return (T) container[index];
    }

    /**
     *
     * @return на выходе массив с новым размером
     */
    private Object[] grow() {
        int oldCapacity = container.length;
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        container = Arrays.copyOf(container, newCapacity);
        return container;
    }

    /**
     * Этот метод отделен от метода ниже, что бы сохранить размер байт-кода метода для компилятора С-1
     * @param t на взоде объект для добавления
     * @param elementData на входе массив куда добавляем
     * @param s на входе размер этого массива
     *          проверяем достаточного ли размера массив, если нет то
     *          увеличиваем его
     *          если достаточного, то объект на входе добавляем в массив
     *          увеличиваем размер на 1
     */
    private void add(T t, Object[] elementData, int s) {
        if (s == elementData.length) {
            elementData = grow();
        }
        elementData[s] = t;
        size = s + 1;
    }

    /**
     * Добавляем элемент в коллекцию
     * @param model элемент который надо добавить
     *              увеличиваем счетчик изменений
     *              добавляем элемент model в массив container, size размера
     */
    public void add(T model) {
        modCount++;
        add(model, container, size);
    }

    @Override
    public Iterator<T> iterator() {
        return new Itr();
    }

    private class Itr implements Iterator<T> {
        int cursor;
        int expectedModCount = modCount;

        public boolean hasNext() {
            return cursor != size;
        }

        @SuppressWarnings("unchecked")
        public T next() {
            int i = cursor;
            if (modCount != expectedModCount) {
                throw new ConcurrentModificationException();
            }
            if (i >= size) {
                throw new NoSuchElementException();
            }
            Object[] elementData = container;
            if (i >= elementData.length) {
                throw new ConcurrentModificationException();
            }
            cursor = i + 1;
            return (T) elementData[i];
        }
    }
}
