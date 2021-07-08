package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLConnection {
    private final String URL = "jdbc:mysql://localhost:3306/casestudy11";
    private final String USERNAME = "root";
    private final String PASSWORD = "123456";

    protected Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection  connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        return connection;
    }
}