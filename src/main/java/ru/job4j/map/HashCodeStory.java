package ru.job4j.map;
/**
 * класс описывает модель данных, в которой присутствуют поля, конструктор, геттеры и переопределенные методы
 * equals и hashCode
 * @author arvik
 * @version 1.0
 * Методы equals  и hashCode переопределенны с помощью автогенерации ide, за исключением того
 * что для поля которое будет сравниваться в иквлз и хэшкод которого будет браться выбран в ручную.
 * По скольку поле pass уникальное в логике данной модели, второго такого быть не может в принципе.
 * Поэтому глядя немного вперед, с уверенностью можно будет сказать что сравнивая и используя вычисления для одного поля
 * ресурсов уйдет намного меньше, учитывая то что полей может прибавиться.
 * return Objects.hash(pass); в данном случае метод hashCode использует функцию хэш, которая генерирует хэшкод так как если бы
 * все входные значения помещены в массив и это массив в последствии хэширован. Под капотом функции
 *  if (a == null)
 *  return 0;
 *  int result = 1;
 *  for (Object element : a)
 *  result = 31 * result + (element == null ? 0 : element.hashCode());
 *  return result;
 *  Что можно использовать для наглядности в методе hashCode() самой модели данных.
 *  Так же для переопределния можно использовать резульат остатка от деления хэш ключа на размер таблицы в которую
 *  планируется поместить данные.
 *
 *
 */

import java.util.HashMap;
import java.util.Objects;

public class HashCodeStory {
    private final String name;
    private final int pass;
    private final String surname;
    private final int bornDate;

    public HashCodeStory(String name, int pass, String surname, int bornDate) {
        this.name = name;
        this.pass = pass;
        this.surname = surname;
        this.bornDate = bornDate;
    }

    public static void main(String[] args) {
        HashCodeStory passSimple1 = new HashCodeStory("Uniq", 123456, "UniqSurname", 1981);
        HashCodeStory passSimple2 = new HashCodeStory("Uniq", 123457, "UniqSurname", 1981);
        HashCodeStory passSimple3 = new HashCodeStory("Uniq", 123456, "UniqSurname", 1981);
        HashMap<HashCodeStory, Object> hashMap = new HashMap<>();
        hashMap.put(passSimple1, new Object());
        hashMap.put(passSimple2, new Object());
        hashMap.put(passSimple3, new Object());
        for (HashCodeStory hashCodeStory : hashMap.keySet()) {
            System.out.println("UniquePassportData: " + hashCodeStory.pass);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        HashCodeStory that = (HashCodeStory) o;
        return pass == that.pass;
    }

    @Override
    public int hashCode() {
        //return Objects.hash(pass);
        int result = 1;
        result = 31 * result + Integer.hashCode(pass);
        return result;
    }

    public String getName() {
        return name;
    }

    public int getPass() {
        return pass;
    }

    public String getSurname() {
        return surname;
    }

    public int getBornDate() {
        return bornDate;
    }

    @Override
    public String toString() {
        return "HashCodeStory{"
                + "name='" + name + '\''
                + ", pass=" + pass
                + ", surname='" + surname + '\''
                + ", bornDate=" + bornDate
                + '}';
    }
}
