package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl extends Util implements UserDao {

    //private Connection getConnection;
    Connection connection = Util.getConnection();

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {

        String sqlCreator = "CREATE TABLE IF NOT EXISTS User (id INT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(255), lastName VARCHAR (255), age INT)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlCreator)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void dropUsersTable() {
        String sqlDropper = "DROP TABLE IF EXISTS User";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlDropper)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String sqlSaver = "INSERT INTO user (name, lastName, age) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlSaver)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void removeUserById(long id) {
        String sqlRemover = "DELETE FROM User WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlRemover)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();

        String sqlGetAllUsrs = "SELECT * FROM user";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlGetAllUsrs);
             ResultSet resSet = preparedStatement.executeQuery()) {
            //
            while (resSet.next()) {
                User user = new User(resSet.getString("name"), resSet.getString("lastName"),
                        resSet.getByte("age"));
                user.setId(resSet.getLong("id"));
                /*user.getName();
                user.getLastName();
                user.getAge();*/
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public void cleanUsersTable() {
        String sqlCleaner = "TRUNCATE User";
        try (/*Prepared*/Statement /*preparedS*/tatement = connection./*prepare*/createStatement(/*sqlCleaner*/)) {
            /*preparedS*/
            tatement.executeUpdate(sqlCleaner);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
