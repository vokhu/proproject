package jm.task.core.jdbc.util;


import java.sql.*;

public class Util {
    public static final String URL = "jdbc:mysql://localhost:3306/mydbtest";
    public static final String USER_NAME = "root";
    public static final String PASSWORD = "123123123";
    public static Connection connection;
    public static Statement statement;

    public static Connection getConnection() {
        try {
            connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
            //System.out.println("connection is ok");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }


}
