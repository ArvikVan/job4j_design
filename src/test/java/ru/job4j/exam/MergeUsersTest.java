package ru.job4j.exam;

import org.junit.Test;

import java.util.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;

public class MergeUsersTest {
    @Test
    public void whenEmailEqualThenMerge() {
        Map<String, Set<String>> input = new HashMap<>();
        input.put("user1", Set.of("xxx@ya.ru", "foo@gmail.com", "lol@mail.ru"));
        input.put("user2", Set.of("foo@gmail.com", "ups@pisem.net"));
        Map<String, Set<String>> out = new HashMap<>();
        out.put("user1", Set.of("xxx@ya.ru", "foo@gmail.com", "lol@mail.ru", "ups@pisem.net"));
        assertThat(MergeUsers.merge(input), is(out));
    }

    @Test
    public void whenNoKey() {
        Map<String, Set<String>> input = new HashMap<>();
        input.put("user1", Set.of("foo@gmail.com", "hoo@gmail.com", "zoo@gmail.com"));
        input.put("user2", Set.of("foo@gmail.com", "hoo@gmail.com", "zoo@gmail.com"));
        Map<String, Set<String>> out = MergeUsers.merge(input);
        assertThat(out.get("user2"), nullValue());
    }
}