package ru.job4j.serialization.java;
/**
 * класс описывает сериализацию и десериализацию
 * Для стандартной сериализации объекта необходимо в классе наследоваться от интерфейса Serializable,
 * этот интерфейс является маркерным, т.е. нет необходимости реализовывать его методы, он сообщает JVM,
 * что объект нашего класса может быть сериализован. Для сериализации объектов в поток используется метод
 * writeObject, для чтения из потока readObject класса ObjectOutputStream.
 * @author arvik
 * @version 1.0
 */

import java.io.*;
import java.nio.file.Files;

public class Contact implements Serializable {
    private static final long serialVersionUID = 1L;
    private final int zipCode;
    private final String phone;

    public Contact(int zipCode, String phone) {
        this.zipCode = zipCode;
        this.phone = phone;
    }

    public int getZipCode() {
        return zipCode;
    }

    public String getPhone() {
        return phone;
    }

    @Override
    public String toString() {
        return "Contact{"
                + "zipCode = " + zipCode
                + ", phone = '" + phone + '\''
                + '}';
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        final Contact contact = new Contact(123456, "+7 (111) 111-11-11");
        /*запись объекта во временный файл, который потом удалится
        * Creates an empty file in the default temporary-file directory,
        * using the given prefix and suffix to generate its name.
        * The resulting Path is associated with the default FileSystem.
        * refix – the prefix string to be used in generating the file's name;
        * may be null
        * suffix – the suffix string to be used in generating the file's name;
        * may be null, in which case ".tmp" is used*/
        File tempFile = Files.createTempFile(null, null).toFile();
        try (FileOutputStream fos = new FileOutputStream(tempFile);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
                oos.writeObject(contact);
        }

        try (FileInputStream fis = new FileInputStream(tempFile);
        ObjectInputStream ois = new ObjectInputStream(fis)) {
            final Contact contactFromFile = (Contact) ois.readObject();
            System.out.println(contactFromFile);
        }
    }
}
