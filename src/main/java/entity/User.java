package entity;

import java.util.List;
import java.util.Objects;

public class User {

    private final int userId;
    private final String firstName;
    private final String lastName;
    private final String phoneNumber;
    private List<Contractor> contractorsList;
    private List<Account> accountList;

    public User(int userId, String firstName, String lastName, String phoneNumber, List<Contractor> contractorsList, List<Account> accountList) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.contractorsList = contractorsList;
        this.accountList = accountList;
    }

    public int getUserId() {
        return userId;
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

    public List<Contractor> getContractorsList() {
        return contractorsList;
    }

    public void setContractorsList(List<Contractor> contractorsList) {
        this.contractorsList = contractorsList;
    }

    public List<Account> getAccountList() {
        return accountList;
    }

    public void setAccountList(List<Account> accountList) {
        this.accountList = accountList;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", contractorsList=" + contractorsList +
                ", accountList=" + accountList +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return userId == user.userId && firstName.equals(user.firstName) && lastName.equals(user.lastName) && phoneNumber.equals(user.phoneNumber) && contractorsList.equals(user.contractorsList) && accountList.equals(user.accountList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, firstName, lastName, phoneNumber, contractorsList, accountList);
    }
}
