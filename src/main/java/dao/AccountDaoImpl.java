package dao;

import config.DatabaseConfig;
import entity.Account;
import entity.Card;
import entity.CardType;
import entity.PaySystem;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AccountDaoImpl implements AccountDao {

    private final static String SQL_FIND_BY_ID = "SELECT * FROM ACCOUNT WHERE ACCOUNT_ID = ?";
    private final static String SQL_FIND_ALL = "SELECT * FROM ACCOUNT";
    private final static String SQL_INSERT_INTO_ACCOUNT = "insert into ACCOUNT (ACCOUNT_ID, ACCOUNT, BALANCE, IS_OPEN, CLIENT_ID) values (DEFAULT, ?, ?, ?, ?)";
    private final static String SQL_UPDATE_BALANCE = "UPDATE ACCOUNT SET BALANCE = ? WHERE ACCOUNT_ID = ?";
    private final static String SQL_FIND_ALL_ACCOUNT_CARDS = "SELECT * FROM CARD as c JOIN ACCOUNT A on A.ACCOUNT_ID = c.ACCOUNT_ID where A.ACCOUNT_ID = ?";


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
                        resultSet.getInt(5),
                        getAccountCards(accountId, connection)));
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
    public List<Account> findAll() {
        List<Account> accountList = new ArrayList<>();
        try (Connection connection = DatabaseConfig.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SQL_FIND_ALL)) {
            while (resultSet.next()) {
                int accountId = resultSet.getInt(1);
                accountList.add(new Account(
                        resultSet.getString(2),
                        resultSet.getBigDecimal(3),
                        resultSet.getBoolean(4),
                        resultSet.getInt(5),
                        getAccountCards(accountId, connection)));
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return accountList;
    }

    @Override
    public void save(Account account) {
        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT_INTO_ACCOUNT)) {
            connection.setSavepoint("start saving account");
            connection.setAutoCommit(false);
            preparedStatement.setString(1, account.getAccount());
            preparedStatement.setBigDecimal(2, account.getBalance());
            preparedStatement.setBoolean(3, account.isOpen());
            preparedStatement.setInt(4, account.getClientId());
            preparedStatement.execute();
            connection.commit();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public void update(Account account) {
    }

    public void updateBalance(int accountId, BigDecimal value) {
        try (Connection connection = DatabaseConfig.getConnection()) {
            Savepoint savepoint = connection.setSavepoint("ready to update balance");
            try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_BALANCE)) {
                connection.setAutoCommit(false);
                preparedStatement.setBigDecimal(1, value);
                preparedStatement.setInt(2, accountId);
                preparedStatement.executeUpdate();
                connection.commit();
            } catch (SQLException exception) {
                connection.rollback(savepoint);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public void delete(int accountId) {
    }

    private List<Card> getAccountCards(int accountId, Connection connection) {
        List<Card> cardList = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_ALL_ACCOUNT_CARDS)) {
            preparedStatement.setInt(1, accountId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                cardList.add(new Card(
                        resultSet.getString(2),
                        CardType.valueOf(resultSet.getString(3)),
                        PaySystem.valueOf(resultSet.getString(4)),
                        resultSet.getBoolean(5),
                        resultSet.getInt(6)));
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return cardList;
    }
}
