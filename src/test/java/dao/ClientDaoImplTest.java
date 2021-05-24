package dao;

import config.DatabaseConfig;
import entity.Account;
import entity.Client;
import entity.Contractor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;


class ClientDaoImplTest {

    @BeforeEach
    void setUp() {
        DatabaseConfig.createTables();
    }

    @Test
    void findById() {

        String name = "Leo";
        Assertions.assertEquals(name, new ClientDaoImpl().findById(1).get().getFirstName());
    }

    @Test
    void findAll() {
        int clientsAmount = 4;
        Assertions.assertEquals(clientsAmount, new ClientDaoImpl().findAll().size());
    }

    @Test
    void save() {
        Client client = new Client("testFirstName", "testLastName", "testPhoneNumber", new ArrayList<Contractor>(), new ArrayList<Account>());
        new ClientDaoImpl().save(client);
        Assertions.assertEquals(client, new ClientDaoImpl().findByPhoneNumber("testPhoneNumber").get());
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    @Test
    void findUsersBankInfo() {
    }

    @Test
    void saveContractor() {
        Contractor contractor = new Contractor("testFirstName", "testLastName", "testPhoneNumber", 1,2);
        new ClientDaoImpl().saveContractor(contractor);
        Client client = new ClientDaoImpl().findById(1).get();
        Assertions.assertEquals(client.getContractorsList().get(0).getFirstName(), contractor.getFirstName());
    }
}