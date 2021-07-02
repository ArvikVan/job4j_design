package ru.job4j.serialization.json;
/**
 * класс описывает Преобразование объекта в JSON и обратно
 * @author arvik
 * @version 1.0
 * дабвлены геттеры
 * изменен main метод преобразованием POJO объекта в JSONObject  и json-строку.
 */
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ChatMembers {
    private final boolean online;
    private final int age;
    private final String chatName;
    private final Contact contact;
    private final String[] members;

    public boolean isOnline() {
        return online;
    }

    public int getAge() {
        return age;
    }

    public String getChatName() {
        return chatName;
    }

    public Contact getContact() {
        return contact;
    }

    public String[] getMembers() {
        return members;
    }

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

        JSONObject jsonContact = new JSONObject("{\"phone\":\"+7(924)111-111-11-11\"}");
        List<String> list = new ArrayList<>();
        list.add("AlexTer");
        list.add("Igor Kovalkov");
        JSONArray jsonMembers = new JSONArray(list);

        final ChatMembers chatMembers = new ChatMembers(true,
                25, "junior", new Contact("11111"), "AlexTer", "Igor Kovalkov");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("online", chatMembers.isOnline());
        jsonObject.put("age", chatMembers.getAge());
        jsonObject.put("chatName", chatMembers.getChatName());
        jsonObject.put("contact", jsonContact);
        jsonObject.put("members", jsonMembers);

        System.out.println(jsonObject.toString());

        System.out.println(new JSONObject(chatMembers).toString());
    }
}
