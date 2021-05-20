package ru.job4j.iterator;

import org.hamcrest.core.Is;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class ListUtilsTest {
    @Test
    public void whenAddBefore() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 1, 2);
        assertThat(Arrays.asList(1, 2, 3), Is.is(input));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenAddBeforeWithInvalidIndex() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 3, 2);
    }

    @Test
    public void whenAddAfterLast() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2));
        ListUtils.addAfter(input, 2, 3);
        assertThat(Arrays.asList(0, 1, 2, 3), Is.is(input));
    }

    @Test
    public void whenRemoveIf() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2, 3));
        ListUtils.removeIf(input, x -> x < 3);
        assertThat(input, Is.is(Collections.singletonList(3)));
    }

    @Test
    public void whenReplaceIf() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2, 3));
        ListUtils.replaceIf(input, x -> x < 3, 11);
        assertThat(input, Is.is(Arrays.asList(11, 11, 11, 3)));
    }

    @Test
    public void whenRemoveAll() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2, 3));
        List<Integer> removingElement = new ArrayList<>(Arrays.asList(0, 3));
        ListUtils.removeAll(input, removingElement);
        assertThat(input, Is.is(Arrays.asList(1, 2)));
    }

}