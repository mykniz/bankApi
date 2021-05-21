package dao;

import config.DatabaseConfig;
import entity.Account;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AccountDaoImpl implements AccountDao {

    private final static String SQL_FIND_BY_ID = "SELECT * FROM ACCOUNT WHERE ACCOUNT_ID = ?";
    private final static String SQL_FIND_ALL = "SELECT * FROM ACCOUNT";
    private final static String SQL_UPDATE_BALANCE = "UPDATE ACCOUNT SET BALANCE = ? WHERE ACCOUNT_ID = ?";

    @Override
    public Optional<Account> findById(int accountId) {
        List<Account> accountList = new ArrayList<>();
        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_BY_ID)) {
            preparedStatement.setInt(1, accountId);                  //todo TRY WITH RES check
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                accountList.add(new Account(
                        resultSet.getString(2),
                        resultSet.getBigDecimal(3),
                        resultSet.getBoolean(4),
                        resultSet.getInt(5)));
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        if (accountList.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of(accountList.get(0));
        }
    }

    @Override
    public List<Account> findAll() {                      //todo check CARD LIST
        List<Account> accountList = new ArrayList<>();
        try (Connection connection = DatabaseConfig.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SQL_FIND_ALL)) {
            while (resultSet.next()) {
                accountList.add(new Account(
                        resultSet.getString(2),
                        resultSet.getBigDecimal(3),
                        resultSet.getBoolean(4),
                        resultSet.getInt(5)));
            }

        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return accountList;
    }

    @Override
    public void save(Account account) {
    }

    @Override
    public void update(Account account) {
    }

    @Override
    public void delete(int accountId) {
    }

    public void updateBalance(int accountId, BigDecimal value) {    //todo DTO no good maybe
        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_BALANCE)) {
            connection.setAutoCommit(false); //todo TRANSACTIONAL
            preparedStatement.setBigDecimal(1, value);
            preparedStatement.setInt(2, accountId);
            preparedStatement.executeUpdate();
            connection.commit(); //todo check roll back
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }
}
