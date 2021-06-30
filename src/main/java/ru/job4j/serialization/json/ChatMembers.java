package ru.job4j.serialization.json;
/**
 * класс описывает Преобразование объекта в JSON и обратно
 * @author arvik
 * @version 1.0
 */
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Arrays;

public class ChatMembers {
    private final boolean online;
    private final int age;
    private final String chatName;
    private final Contact contact;
    private final String[] members;

    public ChatMembers(boolean online, int age, String chatName, Contact contact, String... members) {
        this.online = online;
        this.age = age;
        this.chatName = chatName;
        this.contact = contact;
        this.members = members;
    }

    @Override
    public String toString() {
        return "ChatMembers{"
                + "online=" + online
                + ", age=" + age
                + ", chatName='" + chatName + '\''
                + ", contact=" + contact
                + ", members=" + Arrays.toString(members)
                + '}';
    }

    public static void main(String[] args) {
        final ChatMembers chatMembers = new ChatMembers(true,
                25, "junior", new Contact("11111"), "AlexTer", "Igor Kovalkov");
        /* in JSON*/
        final Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(chatMembers));
        /*in object*/
        final ChatMembers chatMembers1 = gson.fromJson(gson.toJson(chatMembers), ChatMembers.class);
        System.out.println(chatMembers1);
    }
}
