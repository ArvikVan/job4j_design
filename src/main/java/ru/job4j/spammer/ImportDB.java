package ru.job4j.spammer;
/**
 * @author arvikv
 * @version 1.0
 * класс описывает добавление в бд из файла
 */

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ImportDB {
    private Properties cfg;
    private String dump;

    public ImportDB(Properties cfg, String dump) {
        this.cfg = cfg;
        this.dump = dump;
    }

    /**
     * метод описывает чтение из файла в лист
     * @return на выходе заполненный лист
     * @throws IOException ловим ошибки
     * создаем лист в котором будут храниться данные
     * используя trywithresourses читаем файл, пишем в массив в 0 и 1 ячейки деля по ";"
     * записываем в лист соответственно массив0 и массив1
     */
    public List<User> load() throws IOException {
        List<User> users = new ArrayList<>();
        try (BufferedReader rd = new BufferedReader(new FileReader(dump))) {
            rd.lines().forEach(x -> {
                String[] temp = x.split(";");
                users.add(new User(temp[0], temp[1]));
            });
        }
        return users;
    }

    /**
     * метод описывает сохранение нашего заполненного листа в бд
     * @param users на входе лист
     * @throws ClassNotFoundException ловим ошибки
     * @throws SQLException ловим ошибки
     * подсоединяемся к бд, регистрируем драйвер
     * адрес, логин и пароль будут вызываться с точки доступа, тут обозначаем
     * циклом бежим по листу и пишем данные в бд
     */
    public void save(List<User> users) throws ClassNotFoundException, SQLException {
        Class.forName(cfg.getProperty("jdbc.driver"));
        try (Connection cnt = DriverManager.getConnection(
                cfg.getProperty("jdbc.url"),
                cfg.getProperty("jdbc.username"),
                cfg.getProperty("jdbc.password")
        )) {
            for (User user : users) {
                try (PreparedStatement ps = cnt.prepareStatement(
                        "insert into users (name, email) values (?, ?);")) {
                    ps.setString(1, user.name);
                    ps.setString(2, user.email);
                    ps.execute();
                }
            }
        }
    }

    private static class User {
        String name;
        String email;

        public User(String name, String email) {
            this.name = name;
            this.email = email;
        }
    }


    public static void main(String[] args) throws Exception {
        Properties cfg = new Properties();
        try (FileInputStream in = new FileInputStream("./data/importDB.properties")) {
            cfg.load(in);
        }
        ImportDB db = new ImportDB(cfg, "./data/dump.txt");
        db.save(db.load());
    }
}
