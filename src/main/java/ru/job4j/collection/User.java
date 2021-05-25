package ru.job4j.collection;
/**
 * класс описывает модель данных
 * @author arvik
 * @version 1.4
 *  Без переопределения equals и hashCode
 *  Переопределить только hashCode
 *  Переопределить только equals
 *  Переопределить и equals и hashCode
 */

import java.util.*;

public class User {
    private String name;
    private int children;
    private Calendar birthday;

    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "User{"
                + "name='" + name + '\''
                + ", children=" + children
                + ", birthday=" + birthday
                + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return children == user.children && Objects.equals(name, user.name) && Objects.equals(birthday, user.birthday);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, children, birthday);
    }

    public static void main(String[] args) {
        User first = new User("USER", 1,
                new GregorianCalendar(1981, 05, 31));
        User second = new User("USER", 1,
                new GregorianCalendar(1981, 05, 31));
        Map<User, Object> userObjectMap = new HashMap<>();
        userObjectMap.put(first, new Object());
        System.out.println(first.hashCode());
        userObjectMap.put(second, new Object());
        System.out.println(second.hashCode());
        for (Map.Entry<User, Object> user : userObjectMap.entrySet()) {
            System.out.println("key: " + user.getKey());
            System.out.println("hCode: " + user.hashCode());
        }
    }
}
