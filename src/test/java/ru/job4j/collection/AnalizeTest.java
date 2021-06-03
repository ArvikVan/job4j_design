package ru.job4j.collection;

import org.junit.Test;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class AnalizeTest {
    @Test
    public void when1Changed() {
        List<Analize.User> previous = List.of(
                new Analize.User(1, "One"),
                new Analize.User(2, "Two"),
                new Analize.User(3, "Three"),
                new Analize.User(4, "Four"),
                new Analize.User(5, "Five"));
        List<Analize.User> current = List.of(
                new Analize.User(1, "One"),
                new Analize.User(2, "Six"),
                new Analize.User(3, "Three"));
        Analize.Info info = Analize.diff(previous, current);
        assertThat(info.changed, is(1));
        assertThat(info.deleted, is(2));
        assertThat(info.added, is(0));
    }

    @Test
    public void whenAllDelete() {
        List<Analize.User> previous = List.of(
                new Analize.User(1, "One"),
                new Analize.User(2, "Two"),
                new Analize.User(3, "Three"),
                new Analize.User(4, "Four"),
                new Analize.User(5, "Five"));
        List<Analize.User> current = List.of();
        Analize.Info info = Analize.diff(previous, current);
        assertThat(info.deleted, is(5));
        assertThat(info.added, is(0));
        assertThat(info.changed, is(0));
    }

    @Test
    public void whenAllAdd() {
        List<Analize.User> previous = List.of(
                new Analize.User(1, "One"),
                new Analize.User(2, "Two"),
                new Analize.User(3, "Three"),
                new Analize.User(4, "Four"),
                new Analize.User(5, "Five"));
        List<Analize.User> current = List.of(
                new Analize.User(7, "One"),
                new Analize.User(8, "Two"),
                new Analize.User(9, "Three"));
        Analize.Info info = Analize.diff(previous, current);
        assertThat(info.added, is(3));
        assertThat(info.changed, is(0));
        assertThat(info.deleted, is(5));
    }
}