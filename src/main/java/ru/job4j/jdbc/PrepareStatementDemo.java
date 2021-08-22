package ru.job4j.jdbc;
/**
 * @author arvikv
 * @version 1.0
 * класс описывает вставку,обновления, удаления и получения всех элементов
 */

import ru.job4j.io.Config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PrepareStatementDemo {
    private Connection connection;
    public PrepareStatementDemo() throws Exception {
        initConnection();
    }
    /**
     * метод описывает соежинение с бд
     * @throws Exception ошибки ловим
     * регистрируем драйвер в системе
     * указываем путь до файла где хранятся адрес бд, логин, пароль
     */
    private void initConnection() throws Exception {
        Class.forName("org.postgresql.Driver");
        Config config = new Config("./data/app.properties");
        config.load();
        String url = config.value("hibernate.connection.url");
        String login = config.value("hibernate.connection.username");
        String password = config.value("hibernate.connection.password");
        connection =  DriverManager.getConnection(url, login, password);
    }
    public void insert(City city) {
        try (PreparedStatement statement =
                     connection.prepareStatement("insert into cities(name, population) values (?, ?)")) {
            statement.setString(1, city.getName());
            statement.setInt(2, city.getPopulation());
            statement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public boolean update(City city) {
        boolean result = false;
        try (PreparedStatement statement =
                     connection.prepareStatement("update cities set name = ?, population = ? where id = ?")) {
            statement.setString(1, city.getName());
            statement.setInt(2, city.getPopulation());
            statement.setInt(3, city.getId());
            result = statement.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    public boolean delete(int id) {
        boolean result = false;
        try (PreparedStatement statement =
                     connection.prepareStatement("delete from cities where id = ?")) {
            statement.setInt(1, id);
            result = statement.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    public List<City> findAll() {
        List<City> cities = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement("select * from cities")) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    cities.add(new City(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getInt("population")
                    ));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cities;
    }

    public static void main(String[] args) throws Exception {
        List<City> cities = new ArrayList<>();
        City cityLabisk = new City(1, "Labinsk", 50000);
        City cityKurg = new City(2, "Kurganinsk", 45000);
        City cityMaykop = new City(3, "Maykop", 140000);
        PrepareStatementDemo prepareStatementDemo = new PrepareStatementDemo();
        prepareStatementDemo.insert(cityKurg);
        prepareStatementDemo.insert(cityLabisk);
        prepareStatementDemo.insert(cityMaykop);
        cities = prepareStatementDemo.findAll();
        System.out.println(cities.toString());
        prepareStatementDemo.delete(2);
        cities = prepareStatementDemo.findAll();
        System.out.println(cities.toString());
    }

}
