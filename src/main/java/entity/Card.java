package entity;

public class Card {
    //todo cardId
    private final String cardNumber;
    private final CardType cardType;
    private final PaySystem paySystem;
    private final boolean isActive;
    private final int accountId;

    public Card(String cardNumber, CardType cardType, PaySystem paySystem, boolean isActive, int accountId) {
        this.cardNumber = cardNumber;
        this.cardType = cardType;
        this.paySystem = paySystem;
        this.isActive = isActive;
        this.accountId = accountId;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public CardType getCardType() {
        return cardType;
    }

    public PaySystem getPaySystem() {
        return paySystem;
    }

    public boolean isActive() {
        return isActive;
    }

    public int getAccountId() {
        return accountId;
    }
}
