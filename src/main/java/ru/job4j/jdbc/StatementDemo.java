package ru.job4j.jdbc;
/**
 * @author arvikv
 * @version 1.0
 * Класс описывает различные операции после создания бд
 */

import ru.job4j.io.Config;

import java.sql.*;
import java.util.StringJoiner;

public class StatementDemo {
    /**
     *
     * @return на выходе подключаемся к бд
     * @throws Exception исключения на заметке
     */
        private static Connection getConnetion() throws Exception {
            Class.forName("org.postgresql.Driver");
            Config config = new Config("./data/app.properties");
            config.load();
            String url = config.value("hibernate.connection.url");
            String login = config.value("hibernate.connection.username");
            String password = config.value("hibernate.connection.password");
            return DriverManager.getConnection(url, login, password);
        }
        public static void main(String[] args) throws Exception {
            try (Connection connection = getConnetion()) {
                try (Statement statement = connection.createStatement()) {
                    String sql = String.format(
                            "create table if not exists demo_table(%s, %s);",
                            "id serial primary key",
                            "name varchar(255)"
                    );
                    statement.execute(sql);
                    System.out.println(getTableScheme(connection, "demo_table"));
                }
            }
        }

    public static String getTableScheme(Connection connection, String tableName) throws Exception {
        StringBuilder sb = new StringBuilder();
        DatabaseMetaData metaData = connection.getMetaData();
        try (ResultSet columns = metaData.getColumns(null, null, tableName, null)) {
            sb.append(String.format("%-15s %-15s%n", "column", "type"));
            while (columns.next()) {
                sb.append(String.format("%-15s %-15s%n",
                        columns.getString("COLUMN_NAME"),
                        columns.getString("TYPE_NAME")));
            }
        }
        return sb.toString();
    }
}
