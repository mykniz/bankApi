package dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class UserDaoImplTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    void findById() {

        Assertions.assertEquals(1, new UserDaoImpl().findById(1).get().getUserId());

    }

    @Test
    void findAll() {
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