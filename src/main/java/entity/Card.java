package entity;

public class Card {
    private final String number;
    private final Long balance;
    private final CardType cardType;
    private final PaySystem paySystem;

    public Card(String number, Long balance, CardType cardType, PaySystem paySystem) {
        this.number = number;
        this.balance = balance;
        this.cardType = cardType;
        this.paySystem = paySystem;
    }


    public String getNumber() {
        return number;
    }

    public Long getBalance() {
        return balance;
    }

    public CardType getCardType() {
        return cardType;
    }

    public PaySystem getPaySystem() {
        return paySystem;
    }
}
