package service;

import dao.AccountDao;
import dao.UserDao;
import dto.TransactionRequestDto;
import entity.Account;
import entity.Contractor;
import entity.User;

import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

public class AccountService {
    private static final Logger log = Logger.getLogger(AccountService.class.getName());
    private final AccountDao accountDao;
    private final UserDao userDao;

    public AccountService(AccountDao accountDao, UserDao userDao) {
        this.accountDao = accountDao;
        this.userDao = userDao;
    }

    public List<Account> findAll() throws SQLException, FileNotFoundException {
        return accountDao.findAll();
    }

    public Account findById(int accountId) {
        return accountDao.findById(accountId).orElseThrow(() -> new RuntimeException("account not found"));
    }

    public void transferMoney(TransactionRequestDto transactionRequestDto) {
        int accountIdFrom = transactionRequestDto.getAccountIdFrom();
        int accountIdTo = transactionRequestDto.getAccountIdTo();

        if (accountIdFrom != accountIdTo) {       //todo NESTED IF :(((((((
            Account accountFrom = accountDao.findById(accountIdFrom).orElseThrow(() -> new RuntimeException("account not found"));
            Account accountTo = accountDao.findById(accountIdTo).orElseThrow(() -> new RuntimeException("account not found"));
            if (accountFrom.isOpen() && accountTo.isOpen()) {
                User userFrom = userDao.findById(accountFrom.getUserId()).orElseThrow(() -> new RuntimeException("user not found"));
                User userTo = userDao.findById(accountTo.getUserId()).orElseThrow(() -> new RuntimeException("user not found"));
                List<Contractor> contractorList = userFrom.getContractorsList();
                for (Contractor c : contractorList) {
                    if (c.getContractorId() == userTo.getUserId()) {
                        BigDecimal value = transactionRequestDto.getValue();
                        BigDecimal balanceFrom = accountFrom.getBalance();
                        if (balanceFrom.compareTo(value) >= 0) {
                            charge(accountIdFrom, value);
                            topUp(accountIdTo, value);
                        } else {
                            log.info("not enough money on balance");
                        }
                    } else {
                        log.info("contractor not found");
                    }
                }
            } else {
                log.info("the access to accounts is forbidden");
            }
        } else {
            log.info("cannot transfer to same account");
        }
    }

    private void charge(int accountIdFrom, BigDecimal value) {
        accountDao.findById(accountIdFrom).ifPresent(account -> {
            BigDecimal balance = account.getBalance();
            balance = balance.subtract(value);
            accountDao.updateBalance(accountIdFrom, balance);
        });
    }

    public void topUp(int accountIdTo, BigDecimal value) {
        accountDao.findById(accountIdTo).ifPresent(account -> {
            BigDecimal balance = account.getBalance();
            balance = balance.add(value);
            accountDao.updateBalance(accountIdTo, balance);
        });
    }
}
