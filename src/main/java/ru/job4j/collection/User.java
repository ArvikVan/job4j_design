package ru.job4j.collection;
/**
 * класс описывает модель данных
 * @author arvik
 * @version 1.1
 *  Без переопределения equals и hashCode
 */

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

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

    public static void main(String[] args) {
        User first = new User("FirstUser", 1,
                new GregorianCalendar(1981, 05, 31));
        User second = new User("SecondUser", 1,
                new GregorianCalendar(1981, 05, 31));
        Map<User, Object> userObjectMap = new HashMap<>();
        userObjectMap.put(first, new Object());
        userObjectMap.put(second, new Object());
        for (Map.Entry<User, Object> user : userObjectMap.entrySet()) {
            System.out.println("key: " + user.getKey());
            System.out.println("value: " + user.getValue());
        }
    }
}
