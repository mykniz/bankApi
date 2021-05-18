package dto;

import entity.CardType;
import entity.PaySystem;

public class CardDto {

    private final CardType cardType;
    private final PaySystem paySystem;
    private final int accountId;

    public CardDto(CardType cardType, PaySystem paySystem, int accountId) {
        this.cardType = cardType;
        this.paySystem = paySystem;
        this.accountId = accountId;
    }

    public CardType getCardType() {
        return cardType;
    }

    public PaySystem getPaySystem() {
        return paySystem;
    }

    public int getAccountId() {
        return accountId;
    }
}
