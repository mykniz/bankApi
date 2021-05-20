package service;

import dao.AccountDao;
import dto.TopUpRequestDto;
import entity.Account;

import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

public class AccountService {
    private static final Logger log = Logger.getLogger(AccountService.class.getName());
    AccountDao accountDao;

    public AccountService(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    public List<Account> findAll() throws SQLException, FileNotFoundException {
        return accountDao.findAll();
    }

    public void topUp(TopUpRequestDto topUpRequestDto) throws Exception {
        Account account = accountDao.findById(topUpRequestDto.getAccountId()).orElseThrow(() -> new Exception("account not found"));
        BigDecimal newValue = account.getBalance().add(topUpRequestDto.getValue());
        topUpRequestDto.setValue(newValue);
        accountDao.updateBalance(topUpRequestDto);
    }

    public Account findById(int accountId) throws Exception {
        return accountDao.findById(accountId).orElseThrow(() -> new Exception("not found"));
    }
}
