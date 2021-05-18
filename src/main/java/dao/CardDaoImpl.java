package dao;

import entity.Card;
import entity.CardType;
import entity.PaySystem;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CardDaoImpl implements CardDao{
    private final static String SQL_FIND_ALL = "SELECT * FROM CARD";
    private final Connection connection;

    public CardDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Optional<Card> findById(int id) {
        return Optional.empty();
    }

    @Override
    public void save(Card model) {

    }

    @Override
    public void update(Card model) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<Card> findAll() throws SQLException, FileNotFoundException {
        List<Card> listOfCards = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SQL_FIND_ALL);
            while (resultSet.next()) {
                listOfCards.add(new Card(
                        resultSet.getString(2),
                        resultSet.getLong(3),
                        CardType.valueOf(resultSet.getString(4)),
                        PaySystem.valueOf(resultSet.getString(5))));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listOfCards;
    }
}
