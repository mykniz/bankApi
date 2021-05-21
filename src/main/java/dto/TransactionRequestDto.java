package dto;

import java.math.BigDecimal;

public class TransactionRequestDto {

    private final int accountIdFrom;
    private final int accountIdTo;
    private final BigDecimal value;

    public TransactionRequestDto(int accountIdFrom, int accountIdTo, BigDecimal value) {
        this.accountIdFrom = accountIdFrom;
        this.accountIdTo = accountIdTo;
        this.value = value;
    }

    public int getAccountIdFrom() {
        return accountIdFrom;
    }

    public int getAccountIdTo() {
        return accountIdTo;
    }

    public BigDecimal getValue() {
        return value;
    }
}
