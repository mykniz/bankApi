package entity;

import java.math.BigDecimal;
import java.util.List;

public class Account {

    private final String account;
    private final BigDecimal balance;
    private boolean isOpen;
    private final int clientId;
    private final List<Card> cardList;

    public Account(String account, BigDecimal balance, boolean isOpen, int clientId, List<Card> cardList) {
        this.account = account;
        this.balance = balance;
        this.isOpen = isOpen;
        this.clientId = clientId;
        this.cardList = cardList;
    }

    public String getAccount() {
        return account;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    public int getClientId() {
        return clientId;
    }

    public List<Card> getCardList() {
        return cardList;
    }

    @Override
    public String toString() {
        return "Account{" +
                "account='" + account + '\'' +
                ", balance=" + balance +
                ", isOpen=" + isOpen +
                ", clientId=" + clientId +
                ", cardList=" + cardList +
                '}';
    }
}
