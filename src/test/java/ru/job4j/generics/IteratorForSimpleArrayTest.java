package ru.job4j.generics;

import org.junit.Test;
import java.util.NoSuchElementException;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class IteratorForSimpleArrayTest {
    @Test
    public void whenMultiCallhasNextThenTrue() {
        IteratorForSimpleArray it = new IteratorForSimpleArray(
                new Object[] {1, 2, 3}
        );
        assertThat(it.hasNext(), is(true));
        assertThat(it.hasNext(), is(true));
    }

    @Test
    public void whenReadSequence() {
        IteratorForSimpleArray it = new IteratorForSimpleArray(
                new Object[] {1, 2, 3}
        );
        assertThat(it.next(), is(1));
        assertThat(it.next(), is(2));
        assertThat(it.next(), is(3));
    }

    @Test(expected = NoSuchElementException.class)
    public void whenNextFromEmpty() {
        IteratorForSimpleArray it = new IteratorForSimpleArray(
                new Object[] {}
        );
        it.next();
    }

}