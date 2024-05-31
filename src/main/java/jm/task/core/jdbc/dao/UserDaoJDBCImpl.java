package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private static final String SQLCREATE = "CREATE TABLE IF NOT EXISTS users_table (ID BIGINT NOT NULL AUTO_INCREMENT, NAME VARCHAR(100), LASTNAME VARCHAR(100), AGE TINYINT, PRIMARY KEY (ID) );";
    private static final String SQLDELETE = "DROP TABLE IF EXISTS users_table;";
    private static final String SQLADDUSER = "INSERT INTO users_table (name, lastName, age) VALUES (?, ?, ?) ;";
    private static final String SQLREMOVEUSERBYID = "DELETE FROM users_table WHERE ID =(?)";
    private static long id = 0;
    private static final String SQLGETALLUSERS = "SELECT * FROM users_table";
    private static final String SQLCLEANUSESTABLE = "TRUNCATE users_table";
    private static final Connection CONNECTION = Util.getConnection();

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try (Statement statement = CONNECTION.createStatement()) {
            statement.executeUpdate(SQLCREATE);
            System.out.println("Table created!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try (Statement statement = CONNECTION.createStatement()) {
            statement.executeUpdate(SQLDELETE);
            System.out.println("Table dropped!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (PreparedStatement preparedStatement = CONNECTION.prepareStatement(SQLADDUSER)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
            System.out.println("User updated and saved!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) { // prepared statement
        try (PreparedStatement preparedStatement = CONNECTION.prepareStatement(SQLREMOVEUSERBYID)) {
            preparedStatement.setLong (1, id);
            preparedStatement.executeUpdate();
            System.out.println("Used removed!");

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        try (Statement statement = CONNECTION.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SQLGETALLUSERS);
            while (resultSet.next()) {
                User user = new User();
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));
                userList.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    public void cleanUsersTable() {
        try (Statement statement = CONNECTION.createStatement()) {
            statement.executeUpdate(SQLCLEANUSESTABLE);
            System.out.println("Table was truncated!");

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
