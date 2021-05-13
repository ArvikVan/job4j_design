package ru.job4j.generics;

import org.junit.Test;

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


}