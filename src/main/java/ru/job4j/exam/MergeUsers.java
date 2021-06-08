package ru.job4j.exam;
/**
 * кдасс описывает слияние пользователей с их мылами
 * @author arvik
 * @version 1.0
 */

import java.util.*;

public class MergeUsers {
    /**
     *
     * @param mergeOfUsers на входе карта пользователей с их емайлами
     * @return на выходе результат слияния пользователей
     * со0здаем карту in и добавляем в нее значения mergeOfUsers
     * создаем карту out в которой будет результат слияния
     * бежим по in и заполняем out
     * добавляем значения по ключу(имя) и значению(множество почт)
     *
     * бежим по out и добавляем в set все элементы из inElement и outElement которых нет
     * если сумма уникальных почт и сумма почт inElement и outElement не равны, то
     * in и out равны
     * записываем уникальные почты в in, ключ в out
     * добавляем в щге ключ, значение
     * возвращаем результат слияния
     */
    public static Map<String, Set<String>> merge(Map<String, Set<String>> mergeOfUsers) {
        Map<String, Set<String>> in = new HashMap<>(mergeOfUsers);
        Map<String, Set<String>> out = new HashMap<>();
        for (Map.Entry<String, Set<String>> inElement: in.entrySet()) {
                if (out.isEmpty()) {
                out.put(inElement.getKey(), inElement.getValue());
                continue;
            }
            for (Map.Entry<String, Set<String>> outElement: out.entrySet()) {
                Set<String> set = new HashSet<>();
                set.addAll(inElement.getValue());
                set.addAll(outElement.getValue());
                int fullSize = outElement.getValue().size()
                        + inElement.getValue().size();
                if (fullSize != set.size()) {
                    inElement.setValue(set);
                }
                out.put(outElement.getKey(), inElement.getValue());
            }
        }
        return out;
    }
}
