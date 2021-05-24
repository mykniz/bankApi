package dao;

import config.DatabaseConfig;
import entity.Account;
import entity.Card;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.util.ArrayList;

class AccountDaoImplTest {

    @BeforeEach
    void setUp() {
        DatabaseConfig.createTables();
    }

    @Test
    void findById() {
        Assertions.assertEquals("64419815581464276017", new AccountDaoImpl().findById(1).get().getAccount());
    }

    @Test
    void findAll() {
        Assertions.assertEquals(6, new AccountDaoImpl().findAll().size());
    }

    @Test
    void save() {
        Account account = new Account("testAccountNumber", BigDecimal.valueOf(1000), false, 1, new ArrayList<Card>());
        new AccountDaoImpl().save(account);
        Assertions.assertNotNull(new AccountDaoImpl().findAll());
    }

    @Test
    void update() {
    }

    @Test
    void updateBalance() {
        Account account = new AccountDaoImpl().findById(1).get();
        BigDecimal balance = account.getBalance();
        balance = balance.add(BigDecimal.valueOf(5));
        new AccountDaoImpl().updateBalance(1, balance);
        Assertions.assertEquals(balance, new AccountDaoImpl().findById(1).get().getBalance());
    }

    @Test
    void delete() {
    }
}