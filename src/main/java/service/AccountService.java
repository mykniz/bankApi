package service;

import dao.AccountDao;
import dao.CardDao;
import entity.Account;
import entity.Card;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.List;

public class AccountService {
    AccountDao accountDao;

    public AccountService(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    public List<Account> findAll() throws SQLException, FileNotFoundException {
        return accountDao.findAll();
    }
}
