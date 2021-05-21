package dao;

import config.DatabaseConfig;
import dto.UserBankInfoResponseDto;
import entity.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDaoImpl implements UserDao {

    private final static String SQL_FIND_USER_BY_ID = "SELECT * FROM USER WHERE USER_ID = ?";
    private final static String SQL_FIND_ALL_USER_CONTRACTORS = "SELECT * FROM CONTRACTOR WHERE USER_ID = ?";
    private final static String SQL_FIND_ALL_USER_ACCOUNTS = "SELECT * FROM ACCOUNT join USER U on U.USER_ID = ACCOUNT.USER_ID WHERE U.USER_ID = ?";
    private final static String SQL_FIND_ALL_ACCOUNT_CARDS = "SELECT * FROM CARD as c JOIN ACCOUNT A on A.ACCOUNT_ID = c.ACCOUNT_ID where A.ACCOUNT_ID = ?";

    private final static String SQL_FIND_ALL_USERS = "SELECT * FROM USER";

    private final static String SQL_FIND_USERS_BANK_INFO =
            "select U.FIRST_NAME, U.LAST_NAME, A.BALANCE, C.NUMBER, C.CARD_TYPE, C.PAY_SYSTEM FROM CARD AS C" +
                    "         JOIN ACCOUNT A on A.ACCOUNT_ID = C.ACCOUNT_ID" +
                    "         join USER U on U.USER_ID = A.USER_ID";
    private final static String SQL_INSERT_INTO_CONTRACTOR =
            "insert into CONTRACTOR (id, first_name, last_name, phone_number, USER_ID, CONTRACTOR_ID) values (DEFAULT, ?, ?, ?, ?, ?)";


    @Override
    public Optional<User> findById(int userId) {
        List<User> userList = new ArrayList<>();
        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_USER_BY_ID)) {
            preparedStatement.setInt(1, userId);                  //todo TRY WITH RES check
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                userList.add(new User(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        getUserContractors(userId),
                        getUserAccounts(userId)));
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        if (userList.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of(userList.get(0));
        }
    }


    @Override
    public List<User> findAll() {
        List<User> listOfUsers = new ArrayList<>();
        try (Connection connection = DatabaseConfig.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SQL_FIND_ALL_USERS);
            while (resultSet.next()) {
                int userId = resultSet.getInt(1);
                listOfUsers.add(
                        new User(resultSet.getInt(1),
                                resultSet.getString(2),
                                resultSet.getString(3),
                                resultSet.getString(4),
                                getUserContractors(userId),
                                getUserAccounts(userId)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listOfUsers;
    }


    @Override
    public void save(User model) {

    }

    @Override
    public void update(User model) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public List<UserBankInfoResponseDto> findUsersBankInfo() {

        List<UserBankInfoResponseDto> listOfUsers = new ArrayList<>();
        try (Connection connection = DatabaseConfig.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SQL_FIND_USERS_BANK_INFO);
            while (resultSet.next()) {
                listOfUsers.add(new UserBankInfoResponseDto(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getBigDecimal(3),
                        resultSet.getString(4),
                        CardType.valueOf(resultSet.getString(5)),
                        PaySystem.valueOf(resultSet.getString(6))));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listOfUsers;
    }

    @Override
    public void saveContractor(User contractor, int userId, int contractorId) {
        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT_INTO_CONTRACTOR)) {
            connection.setSavepoint("start saving contractor");
            connection.setAutoCommit(false);
            preparedStatement.setString(1, contractor.getFirstName());
            preparedStatement.setString(2, contractor.getLastName());
            preparedStatement.setString(3, contractor.getPhoneNumber());
            preparedStatement.setInt(4, userId);
            preparedStatement.setInt(5, contractorId);
            preparedStatement.execute();
            connection.commit();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    private List<Contractor> getUserContractors(int userId) {
        List<Contractor> contractorList = new ArrayList<>();
        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_ALL_USER_CONTRACTORS)) {
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                contractorList.add(new Contractor(
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getInt(5),
                        resultSet.getInt(6)));
            }

        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return contractorList;
    }

    private List<Account> getUserAccounts(int userId) {
        List<Account> accountList = new ArrayList<>();
        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_ALL_USER_ACCOUNTS)) {
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int accountId = resultSet.getInt(1);
                accountList.add(new Account(
                        resultSet.getString(2),
                        resultSet.getBigDecimal(3),
                        resultSet.getBoolean(4),
                        resultSet.getInt(5),
                        getAccountCards(accountId)));
            }

        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return accountList;
    }


    private List<Card> getAccountCards(int accountId) {
        List<Card> cardList = new ArrayList<>();
        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_ALL_ACCOUNT_CARDS)) {
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

