package ru.job4j.tree;
/**
 * интерфейс описывающий дерево
 * @author arvik
 * @version 1.0
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public interface Tree<E> {
    /**
     * Должен находить узел по значению parent и добавлять в него дочерний узел со значением child.
     * @param parent на входе узел который нужно найти
     * @param child на взоде узел который надо добавить
     * @return на выходе имеем узел со значением child
     */
    boolean add(E parent, E child);
    Optional<Node<E>> findBy(E value);

    /**
     * класс Node описывает узел дерева. Узел содержит хранимое значение и ссылки на дочерние узлы.
     * @param <E>
     */
    class Node<E> {
        final E value;
        final List<Node<E>> children = new ArrayList<>();

        public Node(E value) {
            this.value = value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Node<?> node = (Node<?>) o;
            return Objects.equals(value, node.value) && Objects.equals(children, node.children);
        }

        @Override
        public int hashCode() {
            return Objects.hash(value, children);
        }
    }

}
