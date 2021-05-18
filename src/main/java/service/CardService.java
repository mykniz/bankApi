package service;

import dao.CardDao;
import entity.Card;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.List;

public class CardService {
    CardDao cardDao;

    public CardService(CardDao cardDao) {
        this.cardDao = cardDao;
    }

    public List<Card> findAll() throws SQLException, FileNotFoundException {
        return cardDao.findAll();
    }

    public Card newCardApply() {
        return null;
    }
}
