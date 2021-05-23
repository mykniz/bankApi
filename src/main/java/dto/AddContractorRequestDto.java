package dto;

public class AddContractorRequestDto {

    private final int clientId;
    private final int contractorId;

    public AddContractorRequestDto(int clientId, int contractorId) {
        this.clientId = clientId;
        this.contractorId = contractorId;
    }

    public int getClientId() {
        return clientId;
    }

    public int getContractorId() {
        return contractorId;
    }
}
