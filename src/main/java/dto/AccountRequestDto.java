package dto;

public class AccountRequestDto {
    private final int clientId;

    public AccountRequestDto(int clientId) {
        this.clientId = clientId;
    }

    public int getClientId() {
        return clientId;
    }
}
