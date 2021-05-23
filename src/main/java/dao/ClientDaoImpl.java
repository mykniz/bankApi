package dao;

import config.DatabaseConfig;
import dto.ClientBankInfoResponseDto;
import entity.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ClientDaoImpl implements ClientDao {

    private final static String SQL_FIND_CLIENT_BY_ID = "SELECT * FROM CLIENT WHERE CLIENT_ID = ?";
    private final static String SQL_FIND_CLIENT_BY_PHONE_NUMBER = "SELECT * FROM CLIENT WHERE PHONE_NUMBER = ?";
    private final static String SQL_FIND_ALL_CLIENT_CONTRACTORS = "SELECT * FROM CONTRACTOR WHERE CLIENT_ID = ?";
    private final static String SQL_FIND_ALL_CLIENT_ACCOUNTS = "SELECT * FROM ACCOUNT join CLIENT c on c.CLIENT_ID = ACCOUNT.CLIENT_ID WHERE c.CLIENT_ID = ?";
    private final static String SQL_FIND_ALL_ACCOUNT_CARDS = "SELECT * FROM CARD as c JOIN ACCOUNT A on A.ACCOUNT_ID = c.ACCOUNT_ID where A.ACCOUNT_ID = ?";
    private final static String SQL_FIND_ALL_CLIENTS = "SELECT * FROM CLIENT";
    private final static String SQL_FIND_CLIENTS_BANK_INFO =
            "select CLIENT.FIRST_NAME, CLIENT.LAST_NAME, A.BALANCE, C.NUMBER, C.CARD_TYPE, C.PAY_SYSTEM FROM CARD AS C" +
                    "         JOIN ACCOUNT A on A.ACCOUNT_ID = C.ACCOUNT_ID" +
                    "         join CLIENT on CLIENT.CLIENT_ID = A.CLIENT_ID";
    private final static String SQL_INSERT_INTO_CONTRACTOR = "insert into CONTRACTOR (id, first_name, last_name, phone_number, CLIENT_ID, CONTRACTOR_ID) values (DEFAULT, ?, ?, ?, ?, ?)";
    private final static String SQL_INSERT_INTO_CLIENT = "insert into CLIENT (CLIENT_ID, first_name, last_name, phone_number) values (DEFAULT, ?, ?, ?)";


    @Override
    public Optional<Client> findById(int clientId) {
        List<Client> clientList = new ArrayList<>();
        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_CLIENT_BY_ID)) {
            preparedStatement.setInt(1, clientId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                clientList.add(new Client(
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        getUserContractors(clientId, connection),
                        getUserAccounts(clientId, connection)));
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        if (clientList.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of(clientList.get(0));
        }
    }


    public Optional<Client> findByPhoneNumber(String phoneNumber) {
        List<Client> clientList = new ArrayList<>();
        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_CLIENT_BY_PHONE_NUMBER)) {
            preparedStatement.setString(1, phoneNumber);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int clientId = resultSet.getInt(1);
                clientList.add(new Client(
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        getUserContractors(clientId, connection),
                        getUserAccounts(clientId, connection)));
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        if (clientList.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of(clientList.get(0));
        }
    }

    @Override
    public List<Client> findAll() {
        List<Client> listOfClients = new ArrayList<>();
        try (Connection connection = DatabaseConfig.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SQL_FIND_ALL_CLIENTS);
            while (resultSet.next()) {
                int clientId = resultSet.getInt(1);
                listOfClients.add(
                        new Client(
                                resultSet.getString(2),
                                resultSet.getString(3),
                                resultSet.getString(4),
                                getUserContractors(clientId, connection),
                                getUserAccounts(clientId, connection)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listOfClients;
    }


    @Override
    public void save(Client client) {
        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT_INTO_CLIENT)) {
            connection.setSavepoint("start saving client");
            connection.setAutoCommit(false);
            preparedStatement.setString(1, client.getFirstName());
            preparedStatement.setString(2, client.getLastName());
            preparedStatement.setString(3, client.getPhoneNumber());
            preparedStatement.execute();
            connection.commit();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public void update(Client client) {

    }

    @Override
    public void delete(int clientId) {

    }

    @Override
    public List<ClientBankInfoResponseDto> findClientsBankInfo() {

        List<ClientBankInfoResponseDto> listOfClients = new ArrayList<>();
        try (Connection connection = DatabaseConfig.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SQL_FIND_CLIENTS_BANK_INFO);
            while (resultSet.next()) {
                listOfClients.add(new ClientBankInfoResponseDto(
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
        return listOfClients;
    }

    @Override
    public void saveContractor(Client contractor, int clientId, int contractorId) {
        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT_INTO_CONTRACTOR)) {
            connection.setSavepoint("start saving contractor");
            connection.setAutoCommit(false);
            preparedStatement.setString(1, contractor.getFirstName());
            preparedStatement.setString(2, contractor.getLastName());
            preparedStatement.setString(3, contractor.getPhoneNumber());
            preparedStatement.setInt(4, clientId);
            preparedStatement.setInt(5, contractorId);
            preparedStatement.execute();
            connection.commit();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    private List<Contractor> getUserContractors(int clientId, Connection connection) {
        List<Contractor> contractorList = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_ALL_CLIENT_CONTRACTORS)) {
            preparedStatement.setInt(1, clientId);
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

    private List<Account> getUserAccounts(int clientId, Connection connection) {
        List<Account> accountList = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_ALL_CLIENT_ACCOUNTS)) {
            preparedStatement.setInt(1, clientId);
            ResultSet resultSet = preparedStatement.executeQuery();
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

