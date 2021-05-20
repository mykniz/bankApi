package dto;

import java.math.BigDecimal;

public class TransactionRequestDto {

    private final int accountId;
    private final String phoneNumber;
    private final BigDecimal value;

    public TransactionRequestDto(int accountId, String phoneNumber, BigDecimal sum) {
        this.accountId = accountId;
        this.phoneNumber = phoneNumber;
        this.value = sum;
    }

    public int getAccountId() {
        return accountId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public BigDecimal getValue() {
        return value;
    }
}
