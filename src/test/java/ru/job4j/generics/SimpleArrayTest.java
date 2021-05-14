package ru.job4j.generics;


import org.junit.Test;
import java.util.Iterator;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class SimpleArrayTest {
    @Test
    public void whenAdd() {
        SimpleArray<String> strings = new SimpleArray<>(3);
        strings.add("first");
        strings.add("sec");
        strings.add("third");
        assertThat(strings.get(2), is("third"));
    }

    @Test
    public void whenSet() {
        SimpleArray<String> strings = new SimpleArray<>(3);
        strings.add("first");
        strings.add("sec");
        strings.add("third");
        strings.set(0, "changeParametr");
        assertThat(strings.get(0), is("changeParametr"));
    }

    @Test
    public void whenRemove() {
        SimpleArray<String> strings = new SimpleArray<>(4);
        strings.add("first");
        strings.add("sec");
        strings.add("third");
        strings.add("fourth");
        strings.remove(3);
        assertThat(strings.get(2), is("third"));
    }

    @Test
    public void whenMultiCallhasNextThenTrue() {
        SimpleArray<Integer> it = new SimpleArray<>(3);
        it.add(5);
        it.add(4);
        it.add(8);
        Iterator iterator = it.iterator();
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.hasNext(), is(true));
    }

    @Test
    public void whenReadSequence() {
        SimpleArray<Integer> it = new SimpleArray<>(3);
        it.add(5);
        it.add(4);
        it.add(8);
        Iterator iterator = it.iterator();
        assertThat(iterator.next(), is(5));
        assertThat(iterator.next(), is(4));
        assertThat(iterator.next(), is(8));
    }

}