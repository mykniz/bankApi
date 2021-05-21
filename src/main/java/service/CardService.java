package service;

import dao.CardDao;
import dto.CardOrderRequestDto;
import entity.Card;
import entity.CardType;
import entity.PaySystem;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class CardService {

    private final CardDao cardDao;
    private static final int CARD_NUMBER_LENGTH = 16;
    private static final int CARD_DIGITS = 10;

    public CardService(CardDao cardDao) {
        this.cardDao = cardDao;
    }

    public List<Card> findAll() {
        List<Card> cardList = new ArrayList<>();
        try {
            cardList = cardDao.findAll();
        } catch (SQLException | FileNotFoundException exception) {
            exception.printStackTrace();
        }
        return cardList;
    }

    public void orderCard(CardOrderRequestDto cardOrderRequestDto) throws FileNotFoundException, SQLException {

        String cardNumber = generateRandom();
        CardType cardType = cardOrderRequestDto.getCardType();
        PaySystem paySystem = cardOrderRequestDto.getPaySystem();
        boolean isActive = false;
        int accountId = cardOrderRequestDto.getAccountId();
        Card card = new Card(cardNumber,cardType,paySystem,isActive,accountId);
        cardDao.save(card);
    }

    private static String generateRandom() {
        StringBuilder stringBuilder = new StringBuilder();
        ThreadLocalRandom threadLocalRandom = ThreadLocalRandom.current(); //todo check method how works
        for (int i = 0; i < CARD_NUMBER_LENGTH; i++) {
            stringBuilder.append(threadLocalRandom.nextInt(CARD_DIGITS));
        }
        return stringBuilder.toString();
    }

    public List<Card> findCardsByAccountId(int accountId) {
        return cardDao.findCardsByAccountId(accountId);
    }
}
