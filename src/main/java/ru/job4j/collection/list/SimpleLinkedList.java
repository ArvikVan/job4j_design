package ru.job4j.collection.list;
/**
 * класс описывает связанный список, LinkedList
 * @author arvik
 * @version 1.0
 */

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleLinkedList<E> implements List<E> {
    /**
     * first; указывает на первый узел
     * last; указывает на последний узел
     */
    private int size = 0;
    private Node<E> first;
    private Node<E> last;
    private int modCount = 0;

    /**
     * пустой конструктор
     */
    public SimpleLinkedList() {

    }

    /**
     * ссылки на последний элемент
     * @param e на вход элемент
     *          присваиваем последнему узлу переменную l
     *          создаем новый узел newNode в который предаем параметры согласно конструктора
     *          присваиваем последний с новым
     *          если последний нулл, то новый становится первый иначе следущий становится новым
     *          увеличиваем счетчики размера списка и счетчик изменений
     *          можно обойтись без него и сразу в add поместить этот код
     */
    void linkLast(E e) {
        final Node<E> l = last;
        final Node<E> newNode = new Node<E>(l, e, null);
        last = newNode;
        if (l == null) {
            first = newNode;
        } else {
            l.next = newNode;
        }
        size++;
        modCount++;
    }

    /**
     * добавляем элемент
     * @param value на вход элемент который надо добавить
     *              передаем в ссылку указывающую на последний элемент наш элемент
     */
    @Override
    public void add(E value) {
        linkLast(value);
    }

    /**
     *
     * @param index на входе индекс
     * @return на выходе элемент из найденой ноды
     */
    @Override
    public E get(int index) {
        checkElementIndex(index);
        return node(index).item;
    }

    /**
     * проверям на попадание в список, не выходим ли за пределы
     * @param index параметр который проверяем
     */
    private void checkElementIndex(int index) {
        if (!isElementIndex(index)) {
            throw new IndexOutOfBoundsException(index);
        }
    }

    /**
     * Указывает, является ли аргумент индексом существующего элемента.
     * @param index  на вход индекс
     * @return если index меньше либо равен 0 и меньше величины списка,
     * то аргумент является индексом существующего элемента
     */
    private boolean isElementIndex(int index) {
        return index >= 0 && index < size;
    }

    /**
     * перебираем ноду
     * @param index на входе индекс
     * @return на выходе узел
     */
    Node<E> node(int index) {
    // assert isElementIndex(index);
    if (index < (size >> 1)) {
        Node<E> x = first;
        for (int i = 0; i < index; i++) {
            x = x.next;
        }
        return x;
    } else {
        Node<E> x = last;
        for (int i = size - 1; i > index; i--) {
            x = x.prev;
        }
        return x;
    }
}

    @Override
    public Iterator<E> iterator() {
        return new SimpleLinkedList.Itr();
    }

    private class Itr implements Iterator<E> {
        SimpleLinkedList.Node<E> actual = first;
        final int expectedModCount = modCount;

        public boolean hasNext() {
            return actual != null;
        }

        @SuppressWarnings("unchecked")
        public E next() {
            if (actual == null) {
                throw new NoSuchElementException();
            }
            E next = actual.item;
            checkForComodification();
            actual = actual.next;
            return next;
        }

        final void checkForComodification() {
            if (modCount != expectedModCount) {
                throw new ConcurrentModificationException();
            }
        }
    }
    /**
     * класс узла
     * @param <E>
     *     элемент, данные которые храним
     *     ссылка на след элемент списка
     *     ссылка на предыдущий элемент списка
     */
    private static class Node<E> {
        Node<E> prev;
        E item;
        Node<E> next;

        public Node(Node<E> prev, E item, Node<E> next) {
            this.item = item;
            this.next = next;
            this.prev = prev;
        }
    }
}
