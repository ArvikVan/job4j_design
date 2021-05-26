package ru.job4j.map;
/**
 * класс описывает мапу
 * @author arvik
 * @version 1.0
 */

import java.util.ConcurrentModificationException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleMap<K, V> implements Map<K, V> {
    private static final float LOAD_FACTOR = 0.75f;
    private int capacity = 8;
    private int count = 0;
    private int modCount = 0;
    private MapEntry<K, V>[] table = new MapEntry[capacity];

    /**
     * метод вставки элемента
     * @param key ключ на входе
     * @param value значение на входе
     * @return true если добавили
     * проверяем достаточная ли вместимость
     * определяем индекс используя хэш
     * если значение индекса есть, то есть по индексу чтото лежит на носу коллизия
     * согласно условию возвращаем false
     * иначе записываем в ячейку по индексу ключ и значение
     * увеличиваем счетчик загрузки мапы и счетчик изменений
     */
    @Override
    public boolean put(K key, V value) {
        if (count >= LOAD_FACTOR * capacity) {
            expand();
        }
        int index = indexFor(hash(key));
        if (table[index] != null) {
            return false;
        } else {
            table[index] = new MapEntry<>(key, value);
            count++;
            modCount++;
            return true;
        }
    }

    /**
     * реализуем хэш функцию
     * @return на выходе
     * В первую очередь определяется хэшкод по ключу, затем вычисляется значение равное нулевому побитовому
     * смещению числа h вправо на 16 бит. И для этих значений выполняется операция побитовое логическое или ^.
     */
    private int hash(Object key) {
        int h = key.hashCode();
        return (key == null) ? 0 : (h) ^ (h >>> 16);
    }

    /**
     * вычисляем индекс куда будет помещен элемент
     * @param hash на входе хэш
     * @return на выходе позиция
     */
    private int indexFor(int hash) {
        return hash & (capacity - 1);
    }
    /**
     * расширяем мапу
     * расширяем мапу путем увеличения размера вдвое
     * создаем новую мапу с новым размером
     * записываем все в нее
     * увеличиваем счетчик изменений
     */
    private void expand() {
        capacity = capacity * 2;
        MapEntry<K, V>[] mapEntry = new MapEntry[capacity];
        for (MapEntry<K, V> kvMapEntry : table) {
            put(kvMapEntry.key, kvMapEntry.value);
        }
        modCount++;
    }

    /**
     *
     * @param key на входе ключ по которому надо вернуть значение
     * @return на выходе значение по ключу
     */
    @Override
    public V get(K key) {
        return table[indexFor(hash(key))] == null ? null : table[indexFor(hash(key))].value;
    }

    /**
     * метод описывает удаление по ключу
     * @param key ключ что надо изничтожить
     * @return на выходе true если получилось, false если нет
     * имеем ключ который надо удалить, находим индекс
     * проверяем тот ли ключ, удаляем (table[index] = null;)
     * уменьшаем счетчик величины, увеличиваем счетчик изменений
     *
     */
    @Override
    public boolean remove(K key) {
        boolean result = false;
        int index = indexFor(hash(key));
        if (table[index].key.equals(key)) {
            table[index] = null;
            count--;
            modCount++;
            result = true;
        }
        return result;
    }

    private static class MapEntry<K, V> {

        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }

    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<K>() {
            final MapEntry<K, V>[] oldTable = table;
            final int expectedModCount = modCount;
            private int point = 0;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                int pointNext = point;
                boolean result = false;
                while (pointNext < oldTable.length) {
                    if (oldTable[pointNext++] != null) {
                        result = true;
                        point = pointNext - 1;
                        break;
                    }
                }
                return result;

            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return oldTable[point++].key;
            }
        };
    }
}
