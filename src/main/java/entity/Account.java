package entity;

import java.math.BigDecimal;

public class Account {

    private final String account;
    private final BigDecimal balance;
    private final boolean isOpen;

    public Account(String account, BigDecimal balance, boolean isOpen) {
        this.account = account;
        this.balance = balance;
        this.isOpen = isOpen;
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
}
