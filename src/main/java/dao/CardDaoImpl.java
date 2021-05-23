package dao;

import config.DatabaseConfig;
import entity.Card;
import entity.CardType;
import entity.PaySystem;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CardDaoImpl implements CardDao {
    private final static String SQL_FIND_ALL =
            "SELECT * FROM CARD";
    private static final String SQL_INSERT_INTO_CARD =
            "INSERT INTO CARD (number, card_type, pay_system, is_active, account_id ) Values (?, ?, ?, ?, ?)";
    private final static String SQL_FIND_CARDS_BY_ACCOUNT_ID =
            "SELECT * FROM CARD WHERE ACCOUNT_ID = ? ";
    private final static String SQL_UPDATE_CARD_STATUS = "UPDATE CARD SET IS_ACTIVE = ? WHERE NUMBER= ?";



    @Override
    public Optional<Card> findById(int id) {
        return Optional.empty();
    }

    @Override
    public List<Card> findCardsByAccountId(int accountId) {
        List<Card> listOfCards = new ArrayList<>();

        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_CARDS_BY_ACCOUNT_ID)) {
            preparedStatement.setInt(1, accountId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                listOfCards.add(new Card(
                        resultSet.getString(2),
                        CardType.valueOf(resultSet.getString(3)),
                        PaySystem.valueOf(resultSet.getString(4)),
                        resultSet.getBoolean(5),
                        resultSet.getInt(6)));
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return listOfCards;
    }

    @Override
    public List<Card> findAll() {
        List<Card> listOfCards = new ArrayList<>();

        try (Connection connection = DatabaseConfig.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SQL_FIND_ALL)) {

            while (resultSet.next()) {
                listOfCards.add(new Card(
                        resultSet.getString(2),
                        CardType.valueOf(resultSet.getString(3)),
                        PaySystem.valueOf(resultSet.getString(4)),
                        resultSet.getBoolean(5),
                        resultSet.getInt(6)));
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return listOfCards;
    }

    @Override
    public void save(Card card) {
        try (Connection connection = DatabaseConfig.getConnection()) {
            Savepoint savepoint = connection.setSavepoint();
            connection.setAutoCommit(false);
            try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT_INTO_CARD)) {
                preparedStatement.setString(1, card.getCardNumber());
                preparedStatement.setString(2, card.getCardType().toString());
                preparedStatement.setString(3, card.getPaySystem().toString());
                preparedStatement.setBoolean(4, card.isActive());
                preparedStatement.setInt(5, card.getAccountId());
                preparedStatement.executeUpdate();
                connection.commit();
            } catch (SQLException e) {
                e.printStackTrace();
                connection.rollback(savepoint);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Card card) {

    }

    @Override
    public void updateStatus(String number, boolean b) {
        try (Connection connection = DatabaseConfig.getConnection()) {
            Savepoint savepoint = connection.setSavepoint("ready to update card status");
            try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_CARD_STATUS)) {
                connection.setAutoCommit(false);
                preparedStatement.setBoolean(1, b);
                preparedStatement.setString(2,number);
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
    public void delete(int id) {

    }
}
