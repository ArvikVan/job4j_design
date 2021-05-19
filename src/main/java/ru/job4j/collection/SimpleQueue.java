package ru.job4j.collection;
/**
 * класс описывает FIFO - first input first output.
 * @param <T>
 * @author arvik
 * @version 1.1
 * Стэк поддерживает вставку только в один конец. pushEnd() удалите
 * ссылки на pushEnd() заменены на push()
 */
public class SimpleQueue<T> {
    /**
     *     private int inSize = 0; размер in стЭка
     *     private int outSize = 0; размер out стЭка
     *     private final SimpleStack<T> in = new SimpleStack<>(); in стЭк
     *     private final SimpleStack<T> out = new SimpleStack<>(); out стЭк
     */
    private int inSize = 0;
    private int outSize = 0;
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();

    /**
     *Метод poll() - должен возвращать первое значение и удалять его из коллекции.
     * @return возвращается первое значение
     *      если размер out стЭка 0, то до тех пор пока размер in стЭка больше 0
     *      добавляем в конец out первый элемент in и удаляем его
     *      увеличиваем размер out
     *      уменьшаем размер in
     * иначе уменьшаем out
     * возвращаем удаляем первый элемент out
     */
    public T poll() {
        if (outSize == 0) {
            while (inSize > 0) {
                out.push(in.pop());
                outSize++;
                inSize--;
            }
        }
        outSize--;
        return out.pop();
    }

    /**
     * Метод push(T value) - помещает значение в конец.
     * @param value значение которое добавляем
     *              увеличиваем размер in
     *              помещаем значение value в конец
     */
    public void push(T value) {
        inSize++;
        in.push(value);
    }

}
