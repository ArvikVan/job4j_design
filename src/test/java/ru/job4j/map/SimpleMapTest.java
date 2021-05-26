package ru.job4j.map;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.*;

public class SimpleMapTest {

    @Test
    public void putEquals() {
        SimpleMap<Integer, Integer> simpleMap = new SimpleMap<>();
        assertTrue(simpleMap.put(1, 1));
        assertFalse(simpleMap.put(1, 1));
    }

    @Test
    public void putDifferent() {
        SimpleMap<Integer, Integer> simpleMap = new SimpleMap<>();
        assertTrue(simpleMap.put(1, 1));
        assertTrue(simpleMap.put(2, 2));
        assertTrue(simpleMap.put(3, 3));
    }

    @Test
    public void get() {
        SimpleMap<Integer, Integer> simpleMap = new SimpleMap<>();
        simpleMap.put(1, 100);
        simpleMap.put(2, 200);
        assertThat(simpleMap.get(1), is(100));
    }

    @Test
    public void getNull() {
        SimpleMap<Integer, Integer> simpleMap = new SimpleMap<>();
        simpleMap.put(1, 100);
        simpleMap.put(2, 200);
        simpleMap.remove(1);
        assertThat(simpleMap.get(1), is(nullValue()));
    }

    @Test
    public void removeFirst() {
        SimpleMap<Integer, Integer> simpleMap = new SimpleMap<>();
        simpleMap.put(1, 100);
        simpleMap.put(2, 200);
        simpleMap.remove(1);
        assertThat(simpleMap.get(1), is(nullValue()));
    }

    @Test
    public void whenInsertThenDelete() {
        SimpleMap<Integer, Integer> table = new SimpleMap<>();
        table.put(1, 1);
        table.put(3, 3);
        assertThat(table.get(1), is(1));
        assertTrue(table.remove(1));
        assertNull(table.get(1));
    }

    @Test
    public void removeSec() {
        SimpleMap<Integer, String> simpleMap = new SimpleMap<>();
        simpleMap.put(1, "one");
        simpleMap.put(2, "two");
        assertTrue(simpleMap.remove(2));
        assertNull(simpleMap.get(2));
    }
}