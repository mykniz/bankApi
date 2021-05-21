package entity;

public class Contractor {
    //todo contractorId
    private final String firstName;
    private final String lastName;
    private final String phoneNumber;
    private final int userId;
    private final int contractorId;

    public Contractor(String firstName, String lastName, String phoneNumber, int userId, int contractorId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.userId = userId;
        this.contractorId = contractorId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public int getUserId() {
        return userId;
    }

    public int getContractorId() {
        return contractorId;
    }

    @Override
    public String toString() {
        return "Contractor{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", userId=" + userId +
                ", contractorId=" + contractorId +
                '}';
    }
}
