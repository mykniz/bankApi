package service;

import dao.CardDao;
import dto.CardOrderRequestDto;
import entity.Card;
import entity.CardType;
import entity.PaySystem;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Logger;

public class CardService {
    private static final Logger log = Logger.getLogger(AccountService.class.getName());
    private final CardDao cardDao;
    private static final int CARD_NUMBER_LENGTH = 16;
    private static final int CARD_DIGITS = 10;

    public CardService(CardDao cardDao) {
        this.cardDao = cardDao;
    }

    public List<Card> findAll() {
        return cardDao.findAll();
    }

    public void orderCard(CardOrderRequestDto cardOrderRequestDto) {

        String cardNumber = generateCardNumber();
        CardType cardType = cardOrderRequestDto.getCardType();
        PaySystem paySystem = cardOrderRequestDto.getPaySystem();
        boolean isActive = false;
        int accountId = cardOrderRequestDto.getAccountId();
        Card card = new Card(cardNumber, cardType, paySystem, isActive, accountId);
        cardDao.save(card);
    }

    private static String generateCardNumber() {
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

    public void changeStatus(String number, String isActive) {
        if(isActive.equalsIgnoreCase("true")) {
            cardDao.updateStatus(number, true);
        }
        if(isActive.equalsIgnoreCase("false")) {
            cardDao.updateStatus(number, false);
        }
    }
}
