package entity;

import java.math.BigDecimal;
import java.util.List;

public class Account {
    //todo accountId
    private final String account;
    private BigDecimal balance;
    private boolean isOpen;
    private final int userId;
    private List<Card> cardList;

    public Account(String account, BigDecimal balance, boolean isOpen, int userId) {
        this.account = account;
        this.balance = balance;
        this.isOpen = isOpen;
        this.userId = userId;
    }

    public Account(String account, BigDecimal balance, boolean isOpen, int userId, List<Card> cardList) {
        this.account = account;
        this.balance = balance;
        this.isOpen = isOpen;
        this.userId = userId;
        this.cardList = cardList;
    }

    public String getAccount() {
        return account;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    public int getUserId() {
        return userId;
    }

    public List<Card> getCardList() {
        return cardList;
    }

    public void setCardList(List<Card> cardList) {
        this.cardList = cardList;
    }

    @Override
    public String toString() {
        return "Account{" +
                "account='" + account + '\'' +
                ", balance=" + balance +
                ", isOpen=" + isOpen +
                ", userId=" + userId +
                ", cardList=" + cardList +
                '}';
    }
}
