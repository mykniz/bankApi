package dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class ClientDaoImplTest {

    @BeforeEach
    void setUp() {
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
    }
}