package service;

import dao.CardDao;
import dto.CardDto;
import entity.Card;
import entity.CardType;
import entity.PaySystem;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class CardService {

    private final CardDao cardDao;
    private static final int CARD_NUMBER_LENGTH = 16;
    private static final int CARD_DIGITS = 10;

    public CardService(CardDao cardDao) {
        this.cardDao = cardDao;
    }

    public List<Card> findAll() throws SQLException, FileNotFoundException {
        return cardDao.findAll();
    }


    public void orderCard(CardDto cardDto) throws FileNotFoundException, SQLException {

        String cardNumber = generateRandom();
        CardType cardType = cardDto.getCardType();
        PaySystem paySystem = cardDto.getPaySystem();
        boolean isActive = false;
        int accountId = cardDto.getAccountId();
        Card card = new Card(cardNumber,cardType,paySystem,isActive,accountId);

        cardDao.save(card);
    }

    private static String generateRandom() {
        StringBuilder stringBuilder = new StringBuilder();
        ThreadLocalRandom threadLocalRandom = ThreadLocalRandom.current();
        for (int i = 0; i < CARD_NUMBER_LENGTH; i++) {
            stringBuilder.append(threadLocalRandom.nextInt(CARD_DIGITS));
        }
        return stringBuilder.toString();
    }
}
