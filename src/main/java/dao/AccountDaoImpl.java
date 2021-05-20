package dao;

import config.DatabaseConfig;
import dto.TopUpRequestDto;
import entity.Account;

import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AccountDaoImpl implements AccountDao {

    private final static String SQL_FIND_BY_ID = "SELECT * FROM ACCOUNT WHERE ACCOUNT_ID = ?";
    private final static String SQL_UPDATE_BALANCE = "UPDATE ACCOUNT SET BALANCE = ? WHERE ACCOUNT_ID = ?";
 //   private final static String SQL_UPDATE_BALANCE2 = "UPDATE ACCOUNT SET balance = 300 WHERE ACCOUNT_ID = 7";
    private final static String SQL_FIND_ALL = "SELECT * FROM ACCOUNT";


    @Override
    public Optional<Account> findById(int accountId) {

        List<Account> userList = new ArrayList<>();

        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_BY_ID)) {
            preparedStatement.setInt(1, accountId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                userList.add(new Account(
                        resultSet.getString(2),
                        resultSet.getBigDecimal(3),
                        resultSet.getBoolean(4),
                        resultSet.getInt(5)));
            }
        } catch (SQLException | FileNotFoundException exception) {
            exception.printStackTrace();
        }

        if (userList.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of(userList.get(0));
        }
    }

    @Override
    public void save(Account account) {
    }

    public void updateBalance(TopUpRequestDto topUpRequestDto) {    //todo DTO no good maybe
        try (Connection connection = DatabaseConfig.getConnection();
             ) {

            PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_BALANCE);
            preparedStatement.setBigDecimal(1, topUpRequestDto.getValue());
            preparedStatement.setInt(2, topUpRequestDto.getAccountId());
            preparedStatement.executeUpdate();

//
//            Statement statement = connection.createStatement();
//            statement.execute(SQL_UPDATE_BALANCE2);
        } catch (SQLException | FileNotFoundException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public void update(Account account) {
    }

    @Override
    public void delete(int accountId) {

    }

    @Override
    public List<Account> findAll() {

        List<Account> accountList = new ArrayList<>();
        try (Connection connection = DatabaseConfig.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SQL_FIND_ALL);
            while (resultSet.next()) {
                accountList.add(new Account(
                        resultSet.getString(2),
                        resultSet.getBigDecimal(3),
                        resultSet.getBoolean(4),
                        resultSet.getInt(5)));
            }
        } catch (SQLException | FileNotFoundException exception) {
            exception.printStackTrace();
        }
        return accountList;
    }
}
