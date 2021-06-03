package ru.job4j.collection;
/**
 * класс описывает сравнение двух листов
 * @author arvik
 * @version 1.0
 */

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Analize {
    /**
     *
     * @param previous на входе начальные данные
     * @param current на входе измененные данные
     * @return на выходе понять сколько:
     * добавлено новых пользователей
     * Сколько измененно пользователей
     * Сколько удалено пользователей
     *
     * создадим мапу и поместим в нее previous
     * пробежимся теперь по current и сравниваем каждый элемент current c созданной мапой
     * чтобы узнать совпадают ли ключи, если не совпадают то это новый элемент и надо
     * увеличить added++ из Info
     * если мапа содержит id из текущего и при этом разные name то changed++
     * находим кол-во удаленных элементов, путем вычитания из размера previous, размер current + добавленные элементы
     *
     */
    public static Info diff(List<User> previous, List<User> current) {
        Info info = new Info();
        Map<Integer, String> mapForPrevious = new HashMap<>();
        for (User previousEl : previous) {
            mapForPrevious.put(previousEl.id, previousEl.name);
        }
        for (User currentEl : current) {
            if (!mapForPrevious.containsKey(currentEl.id)) {
                info.added++;
            } else if (mapForPrevious.containsKey(currentEl.id)
                    && !mapForPrevious.get(currentEl.id).equals(currentEl.name)) {
                info.changed++;
            }
        }
        info.deleted = previous.size() - current.size() + info.added;
        return info;
    }

    public static class User {
        int id;
        String name;

        public User(int id, String name) {
            this.id = id;
            this.name = name;
        }
    }

    public static class Info {
        int added;
        int changed;
        int deleted;
    }
}
