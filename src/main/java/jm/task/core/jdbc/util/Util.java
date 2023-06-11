package jm.task.core.jdbc.util;


import jm.task.core.jdbc.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import javax.security.auth.login.AppConfigurationEntry;
//import javax.security.auth.login.Configuration;
import java.sql.*;
import java.util.Properties;

public class Util {
    public static final String URL = "jdbc:mysql://localhost:3306/mydbtest";
    private static final String USER_NAME = "root";
    private static final String PASSWORD = "123123123";
    private static Connection connection;
    private static Statement statement;
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Properties properties = new Properties();
                properties.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
                properties.put(Environment.URL, URL);
                properties.put(Environment.USER, USER_NAME);
                properties.put(Environment.PASS, PASSWORD);
                properties.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
                properties.put(Environment.SHOW_SQL, "true");
                properties.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
                properties.put(Environment.HBM2DDL_AUTO, "");

                sessionFactory = new Configuration().setProperties(properties).addAnnotatedClass(User.class).buildSessionFactory();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return sessionFactory;
    }

    public static Connection getConnection() {
        try {
            connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }
}
