package entity;

public class Account {

    private final String account;
    private final boolean isOpen;

    public Account(String account, boolean isOpen) {
        this.account = account;
        this.isOpen = isOpen;
    }

    public String getAccount() {
        return account;
    }

    public boolean isOpen() {
        return isOpen;
    }
}
