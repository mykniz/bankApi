package entity;

import java.util.List;
import java.util.Objects;

public class Client {

    private final String firstName;
    private final String lastName;
    private final String phoneNumber;
    private List<Contractor> contractorsList;
    private List<Account> accountList;

    public Client(String firstName, String lastName, String phoneNumber, List<Contractor> contractorsList, List<Account> accountList) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.contractorsList = contractorsList;
        this.accountList = accountList;
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
        return "Client{" +
                "firstName='" + firstName + '\'' +
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
        Client client = (Client) o;
        return firstName.equals(client.firstName) && lastName.equals(client.lastName) && phoneNumber.equals(client.phoneNumber) && contractorsList.equals(client.contractorsList) && accountList.equals(client.accountList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, phoneNumber, contractorsList, accountList);
    }
}
