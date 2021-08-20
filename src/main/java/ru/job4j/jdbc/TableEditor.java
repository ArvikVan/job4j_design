package ru.job4j.jdbc;

import ru.job4j.io.Config;

import java.sql.*;
import java.util.Properties;

/**
 * @author arvikv
 * @version 1.0
 * класс описывает работу в бд, создание/удаление таблицы, добавление/удаление/переименование стобцов
 */
public class TableEditor implements AutoCloseable {
    private Connection connection;
    private Properties properties;

    public TableEditor(Properties properties) throws Exception {
        this.properties = properties;
        initConnection();
    }

    /**
     * метод описывает соездинение с бд
     * @throws Exception ошибки ловим
     * указываем путь до файла с настройками
     * и берем оттуда логин, пароль и путь до бд
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

    /**
     * метод описывает создание пустой таблицы без столбцов с указанным именем;
     * @param tableName на входе имя таблицы
     */
    public void createTable(String tableName) throws Exception {
        try (Statement statement = connection.createStatement()) {
            statement.execute(String.format("create table if not exists %s();", tableName));
        }
    }

    /**
     * удаляет таблицу по указанному имени;
     * @param tableName на входе имя таблицы которую необходимо удалить
     */
    public void dropTable(String tableName) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            statement.execute(String.format("drop table %s;", tableName));
        }
    }

    /**
     * добавляет столбец в таблицу;
     * @param tableName на входе имя таблицы в которую надо добавить столб
     * @param columnName на входе имя столбца
     * @param type на входе тип стобца
     */
    public void addColumn(String tableName, String columnName, String type) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            statement.execute(String.format("alter table %s add column %s %s", tableName, columnName, type));
        }
    }

    /**
     * удаляет столбец из таблицы;
     * @param tableName на входе имя таблицы из которой необходимо удалить столбец
     * @param columnName на входе имя столбца который нужно удалить
     */
    public void dropColumn(String tableName, String columnName) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            statement.execute(String.format("alter table %s drop column %s", tableName, columnName));
        }
    }

    /**
     *  переименовывает столбец.
     * @param tableName на входе имя таблицы в которой надо переименовать
     * @param columnName на входе имя стобца который надо переименовать
     * @param newColumnName на входе имя нового столбца
     */
    public void renameColumn(String tableName, String columnName, String newColumnName) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            statement.execute(String.format(
                    "alter table %s rename column %s to %s", tableName, columnName, newColumnName));
        }
    }

    /**
     * смотрим на таблицу в консоли
     * @param connection на входе соединение
     * @param tableName на входе имя таблицы на которую будем смотреть
     * @return на выходе результат
     * @throws Exception имеем ввиду все исключения
     */
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

    public static void main(String[] args) throws Exception {
        Properties properties = new Properties();
        TableEditor tableEditor = new TableEditor(properties);
        tableEditor.dropTable("test_db_statement");
        tableEditor.createTable("test_db_statement");
        tableEditor.addColumn("test_db_statement", "name", "varchar(50)");
        tableEditor.addColumn("test_db_statement", "surname", "varchar(50)");
        tableEditor.addColumn("test_db_statement", "height", "integer");
        tableEditor.addColumn("test_db_statement", "weight", "integer");
        tableEditor.dropColumn("test_db_statement", "weight");
        tableEditor.renameColumn("test_db_statement", "height", "Reheight");
        System.out.println(TableEditor.getTableScheme(tableEditor.connection, "test_db_statement"));
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
}
    }
}
