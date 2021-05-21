package dto;

public class AddContractorRequestDto {

    private final int userId;
    private final int contractorId;

    public AddContractorRequestDto(int userId, int contractorId) {
        this.userId = userId;
        this.contractorId = contractorId;
    }

    public int getUserId() {
        return userId;
    }

    public int getContractorId() {
        return contractorId;
    }
}
