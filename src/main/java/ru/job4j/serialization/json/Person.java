package ru.job4j.serialization.json;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
/**
 * класс описывает Преобразование объекта в JSON и обратно
 * @author arvik
 * @version 1.0
 */


import java.util.Arrays;

public class Person {
    private final boolean sex;
    private final int age;
    private final Contact contact;
    private final String[] statuses;

    public Person(boolean sex, int age, Contact contact, String... statuses) {
        this.sex = sex;
        this.age = age;
        this.contact = contact;
        this.statuses = statuses;
    }

    @Override
    public String toString() {
        return "Person{"
                + "sex=" + sex
                + ", age=" + age
                + ", contact=" + contact
                + ", statuses=" + Arrays.toString(statuses)
                + '}';
    }

    public static void main(String[] args) {
        final Person person = new Person(false, 30, new Contact("11-111"), "worker", "Married");
        /* Преобразуем объект person в json-строку. */
        final Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(person));
        /* Модифицируем json-строку */
        final String personJson =
                "{"
                        + "\"sex\":false,"
                        + "\"age\":35,"
                        + "\"contact\":"
                        + "{"
                        + "\"phone\":\"+7(924)111-111-11-11\""
                        + "},"
                        + "\"statuses\":"
                        + "[\"Student\",\"Free\"]"
                        + "}";
        /* Объект из строки */
        final Person personMode = gson.fromJson(personJson, Person.class);
        System.out.println(personMode);
    }
}