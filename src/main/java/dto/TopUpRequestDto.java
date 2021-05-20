package dto;

import java.math.BigDecimal;

public class TopUpRequestDto {

    private final int accountId;
    private BigDecimal value;

    public TopUpRequestDto(int accountId, BigDecimal value) {
        this.accountId = accountId;
        this.value = value;
    }

    public int getAccountId() {
        return accountId;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }
}
