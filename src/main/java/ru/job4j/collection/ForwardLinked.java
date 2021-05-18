package ru.job4j.collection;
/**
 * класс описывает удаление первого элемента связанного списка
 * @author arvik
 * @version 1.3
 * добавляем метод addFirst()
 * добавляем метод deleteLast()
 * добавили меотд isEmpty()
 * добавили метод revert()
 */

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ForwardLinked<T> implements Iterable<T> {
    /**
     * Node<T> head; первый узел
     * Node<T> tail; последний узел
     */
    private Node<T> head;
    private Node<T> tail;

    /**
     * добавление элемента
     * @param value значение которое надо добавить
     *              созхдается узел у которого ссылка не след элемент null
     *              если голова эквивалентен null, то голова = созданному узлу
     *              потому как изначально на нулл ссылался голова
     * Хвосту присваивается значение головы.
     *              до тех пор пока следущий элемент после хвоста(tail.next) не будет равен null
     *              идем вперед, иначе tail.next присваивается узлу node
     */
    public void add(T value) {
        Node<T> node = new Node<>(value, null);
        if (head == null) {
            head = node;
            return;
        }
        Node<T> tail = head;
        while (tail.next != null) {
            tail = tail.next;
        }
        tail.next = node;
    }

    /**
     * добавление в первый узел
     * @param value значение которое пойдет в голову
     *              создаем ноду, удаляем head путем присовения ее следущему узлу, узел голова
     *              тем самым на первый элемент подменяются ссылки.
     */
    public void addFirst(T value) {
        Node<T> node = new Node<>(value, null);
        node.next = head;
        head = node;
    }

    /**
     * удаление первого элемента
     * @return на выходе значение
     * создаем ноду голова
     * проверяется на NoSuchElementException
     * записываем в голову следующий элемент и далее null
     * потому что если голова нулл, значит ее удалили
     */
    public T deleteFirst() {
        final Node<T> node = head;
        if (head == null) {
            throw new NoSuchElementException();
        }
        head = node.next;
        node.next = null;
        return node.value;
    }

    /**
     * удаляем последний элемент
     * @return на выходе значение
     */
    public T deleteLast() {
        final Node<T> node = tail;
        if (tail == null) {
            throw new NoSuchElementException();
        }
        tail = node.next;
        node.next = null;
        return node.value;
    }

    /**
     * переворачиваем список
     * @return возвращаем тру если перевернули, фалс если нет
     * если список содержит больше одного элемента,
     * для этого если голова не равна null и следущий узел после головы не равен null
     * хвосту присваиваем голову
     * переменную - ссылку на текущий узел и записываем в него head.next
     * в head.next записываем null, потому что это последний элемент списка
     * а теперь будем идти по списку, до тех пор пока текущий элемент не равен null
     * сохраняем ссылку на следущий элемент
     * ссылка на предыдущий элемент хранится в head
     *
     */
    public boolean revert() {
        if (!isEmpty() && head.next != null) {
            tail = head;
            Node<T> current = head.next;
            head.next = null;
            while (current != null) {
                Node<T> next = current.next;
                current.next = head;
                head = current;
                current = next;
            }
        }
        return false;
    }

    /**
     * смотрим пусто ли
     * @return если head null то пустой список
     */
    public boolean isEmpty() {
        return head == null;
    }
    /**
     * итератор
     * @return если голова не равна нулл, то смотрим на след элемент
     *
     */
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            Node<T> node = head;

            @Override
            public boolean hasNext() {
                return node != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T value = node.value;
                node = node.next;
                return value;
            }
        };
    }

    /**
     * описание ноды
     * @param <T>
     */
    private static class Node<T> {
        T value;
        Node<T> next;

        public Node(T value, Node<T> next) {
            this.value = value;
            this.next = next;
        }
    }
}
