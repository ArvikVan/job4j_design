package ru.job4j.collection;

/**
 * класс описывает стЭк
 * реализовать добавление и удаление с одного конца
 * @param <T>
 * @author arvik
 * @version 1.1
 * добавили pushEnd(T value) для удаления последнего элемента
 */
public class SimpleStack<T> {
    private ForwardLinked<T> forwardLinked = new ForwardLinked<>();

    /**
     * Метод pop() - должен возвращать значение и удалять его из коллекции.
     * @return на выходе значение
     * удаляем первый элемент
     */
    public T pop() {
        return forwardLinked.deleteFirst();
    }

    /**
     * Метод push(T value) - помещает значение в коллекцию.
     * @param value добавить его
     *              нужно добавить первый элемент
     */
    public void push(T value) {
        forwardLinked.addFirst(value);
    }

    /**
     * добавляется последний элемент
     * @param value значение которое добавляется
     */
    public void pushEnd(T value) {
        forwardLinked.add(value);
    }
}
