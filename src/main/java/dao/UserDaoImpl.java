package dao;

import config.DatabaseConfig;
import dto.UserDto;
import entity.CardType;
import entity.PaySystem;
import entity.User;

import java.io.FileNotFoundException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDaoImpl implements UserDao {

    private final static String SQL_FIND_ALL_USERS = "SELECT * FROM USER";
    private final static String SQL_FIND_USERS_BANK_INFO =
            "select U.FIRST_NAME, U.LAST_NAME, A.BALANCE, C.NUMBER, C.CARD_TYPE, C.PAY_SYSTEM FROM CARD AS C" +
                    "         JOIN ACCOUNT A on A.ACCOUNT_ID = C.ACCOUNT_ID" +
                    "         join USER U on U.USER_ID = A.USER_ID;";


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
    public List<User> findAll() {
        List<User> listOfUsers = new ArrayList<>();
        try (Connection connection = DatabaseConfig.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SQL_FIND_ALL_USERS);
            while (resultSet.next()) {
                listOfUsers.add(new User(resultSet.getString(2), resultSet.getString(3)));
            }
        } catch (SQLException | FileNotFoundException e) {
            e.printStackTrace();
        }
        return listOfUsers;
    }

    @Override
    public List<UserDto> findUsersBankInfo() {

        List<UserDto> listOfUsers = new ArrayList<>();
        try (Connection connection = DatabaseConfig.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SQL_FIND_USERS_BANK_INFO);
            while (resultSet.next()) {
                listOfUsers.add(new UserDto(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getBigDecimal(3),
                        resultSet.getString(4),
                        CardType.valueOf(resultSet.getString(5)),
                        PaySystem.valueOf(resultSet.getString(6))));
            }
        } catch (SQLException | FileNotFoundException e) {
            e.printStackTrace();
        }
        return listOfUsers;
    }


}