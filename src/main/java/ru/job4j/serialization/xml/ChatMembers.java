package ru.job4j.serialization.xml;

/**
 * класс описывает сериализовацию/десериализоцию объекта в/c XML
 * @author arvik
 * @version 1.0
 * создаем конструктор дефолтный
 * у полей убираем final
 * к полям добавляем атрибуты
 * к массиву @XmlElementWrapper(name = "members")
 *           @XmlElement(name = "member") для отображения вложенных тегов
 *
 */

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.*;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Arrays;

@XmlRootElement(name = "chatMembers")
@XmlAccessorType(XmlAccessType.FIELD)
public class ChatMembers {

    @XmlAttribute
    private boolean online;

    @XmlAttribute
    private int age;

    @XmlAttribute
    private String chatName;

    private Contact contact;

    @XmlElementWrapper(name = "members")
    @XmlElement(name = "member")
    private String[] members;

    public ChatMembers() {
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

    public static void main(String[] args) throws Exception {
        final ChatMembers chatMembers = new ChatMembers(true,
                25, "junior", new Contact("@junior"), "AlexTer", "Igor Kovalkov");
        // Получаем контекст для доступа к АПИ
        JAXBContext context = JAXBContext.newInstance(ChatMembers.class);
        // Создаем сериализатор
        Marshaller marshaller = context.createMarshaller();
        // Указываем, что нам нужно форматирование
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        // Сериализуем
        String xml = "";
        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(chatMembers, writer);
            xml = writer.getBuffer().toString();
            System.out.println(xml);
        }
        // Для десериализации нам нужно создать десериализатор
        Unmarshaller unmarshaller = context.createUnmarshaller();
        try (StringReader reader = new StringReader(xml)) {
            // десериализуем
            ChatMembers result = (ChatMembers) unmarshaller.unmarshal(reader);
            System.out.println(result);
        }

    }
}
