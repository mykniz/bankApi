package dao;

import config.DatabaseConfig;
import dto.CardDto;
import entity.Card;
import entity.CardType;
import entity.PaySystem;

import java.io.FileNotFoundException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CardDaoImpl implements CardDao {
    private final static String SQL_FIND_ALL =
            "SELECT * FROM CARD";
    private static final String SQL_INSERT_INTO_CARD =
            "INSERT INTO CARD (number, card_type, pay_system, is_active, account_id ) Values (?, ?, ?, ?, ?)";


    @Override
    public Optional<Card> findById(int id) {
        return Optional.empty();
    }

    @Override
    public void save(Card card) {

        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT_INTO_CARD)) {

            preparedStatement.setString(1, card.getCardNumber());
            preparedStatement.setString(2, card.getCardType().toString());
            preparedStatement.setString(3, card.getPaySystem().toString());
            preparedStatement.setBoolean(4, card.isActive());
            preparedStatement.setInt(5, card.getAccountId());
            preparedStatement.executeUpdate();
        } catch (SQLException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Card model) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<Card> findAll() throws SQLException, FileNotFoundException {
        Connection connection = DatabaseConfig.getConnection();
        List<Card> listOfCards = new ArrayList<>();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(SQL_FIND_ALL);
        while (resultSet.next()) {
            listOfCards.add(new Card(
                    resultSet.getString(2),
                    CardType.valueOf(resultSet.getString(3)),
                    PaySystem.valueOf(resultSet.getString(4)),
                    resultSet.getBoolean(5),
                    resultSet.getInt(6)));
        }

        return listOfCards;
    }
}
