package ru.job4j.io.findbycriteria;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class ArgsNameTest {
    @Test
    public void whenGetFirst() {
        ArgsName jvm = ArgsName.of(new String[] {"-d=c:/", "-n=*.txt", "-t=mask", "-o=log.txt"});
        assertThat(jvm.get("d"), is("c:/"));
    }

    @Test
    public void whenGetFirstReorder() {
        ArgsName jvm = ArgsName.of(new String[] {"-encoding=UTF-8", "-Xmx=512", "-t=mask", "-o=log.txt"});
        assertThat(jvm.get("Xmx"), is("512"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenWrongSomeArgument() {
        ArgsName jvm = ArgsName.of(new String[] {"-enconding=UTF-8", "-Xmx="});
    }

}