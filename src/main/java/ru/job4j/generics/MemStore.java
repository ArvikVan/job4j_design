package ru.job4j.generics;
/**
 * класс описывает каркас хранилища
 * @author arvik
 * @version 1.1
 * поиск определен один раз в методе и заменен там где используется
 * int index = indexOf(id);
 */

import java.util.ArrayList;
import java.util.List;

public final class MemStore<T extends Base> implements Store<T> {
    /**
     * список - хранилище, с которым будет вестись работа
     */
    private final List<T> mem = new ArrayList<>();

    /**
     * реализуем метод  добавления
     * @param model на вход элемент который надо добавить
     * mem.add(model); добавляем в список элемент
     */
    @Override
    public void add(T model) {
        mem.add(model);
    }

    /**
     * метод поиска первого похожего элемента
     * @param id на вход ид
     * @return на выход либо -1 если не нашли, или индекс когда нашли
     * int rsl = -1; условно не нашли, дальше если найдем изменим
     * бежим по циклу, на каждой итерации сравнивая id, как только совпадение
     * присваиваем result индекс на котором нашли совпадение
     */
    public int indexOf(String id) {
        int result = -1;
        for (int i = 0; i < mem.size(); i++) {
            if (mem.get(i).getId().equals(id)) {
                result = i;
                break;
            }
        }
        return result;
    }

    /**
     * реализуем метод перемещения
     * @param id на вход ид который надо перенести
     * @param model на вход элемент
     * @return на выходе тру если переместили, фалс если нет
     * if (indexOf(id) != -1) если нашли совпадение то устанавливаем в этот индекс модель
     * result = true; возвращаем тру потому что получилось
     */
    @Override
    public boolean replace(String id, T model) {
        boolean result = false;
        int index = indexOf(id);
        if (index != -1) {
            mem.set(index, model);
            result = true;
        }
        return result;
    }

    /**
     * удаление
     * @param id на вход ид который надо удалить
     * @return на выходе тру если удалили, если нет то фалс
     * if (indexOf(id) != -1) если совпадение нашли, то
     * mem.remove(indexOf(id)); ужадяем элемент индекса по которому совпало
     * и возвращаем тру, потому что сработало
     */
    @Override
    public boolean delete(String id) {
        boolean rsl = false;
        int index = indexOf(id);
        if (index != -1) {
            mem.remove(index);
            rsl = true;
        }
        return rsl;
    }

    /**
     * поиск по ид
     * @param id на вход id окторый надо найти
     * @return на выходу елемент
     * если совпадение нашли то показываем его, если нет то null
     *
     */
    @Override
    public T findById(String id) {
        int index = indexOf(id);
        if (index != -1) {
            return mem.get(index);
        }
        return null;
    }
}
