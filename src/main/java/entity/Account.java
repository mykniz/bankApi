package entity;

import java.math.BigDecimal;

public class Account {

    private final String account;
    private BigDecimal balance;
    private boolean isOpen;
    private final int userId;

    public Account(String account, BigDecimal balance, boolean isOpen, int userId) {
        this.account = account;
        this.balance = balance;
        this.isOpen = isOpen;
        this.userId = userId;
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

    public int getUserId() {
        return userId;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }


    @Override
    public String toString() {
        return "Account{" +
                "account='" + account + '\'' +
                ", balance=" + balance +
                ", isOpen=" + isOpen +
                ", userId=" + userId +
                '}';
    }
}
