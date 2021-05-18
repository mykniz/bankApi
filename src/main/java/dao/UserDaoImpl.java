package dao;

import config.DatabaseConfig;
import entity.User;

import java.io.FileNotFoundException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDaoImpl implements UserDao {

    private final static String SQL_FIND_ALL = "SELECT * FROM USER";

    private final Connection connection;

    public UserDaoImpl(Connection connection) {
        this.connection = connection;
    }


    @Override
    public Optional<User> findById(int id) {
        return Optional.empty();
    }

    @Override
    public void save(User model) {

    }

    @Override
    public void update(User model) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<User> findAll() throws SQLException, FileNotFoundException {
        List<User> listOfUsers = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SQL_FIND_ALL);
            while (resultSet.next()) {
                listOfUsers.add(new User(resultSet.getString(2), resultSet.getString(3)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listOfUsers;
    }



}