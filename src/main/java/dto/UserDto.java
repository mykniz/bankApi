package dto;

import entity.CardType;
import entity.PaySystem;

import java.math.BigDecimal;

public class UserDto {

    private final String firstName;
    private final String lastName;
    private final BigDecimal balance;
    private final String cardNumber;
    private final CardType cardType;
    private final PaySystem paySystem;

    public UserDto(String firstName, String lastName, BigDecimal balance, String cardNumber, CardType cardType, PaySystem paySystem) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.balance = balance;
        this.cardNumber = cardNumber;
        this.cardType = cardType;
        this.paySystem = paySystem;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public BigDecimal getBalance() {
        return balance;
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
}
