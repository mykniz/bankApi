package service;

import dao.AccountDao;
import dao.ClientDao;
import dto.AccountRequestDto;
import dto.TransactionRequestDto;
import entity.Account;
import entity.Card;
import entity.Client;
import entity.Contractor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Logger;

public class AccountService {
    private static final Logger log = Logger.getLogger(AccountService.class.getName());
    private static final int ACCOUNT_NUMBER_LENGTH = 20;
    private static final int CARD_DIGITS = 10;
    private final AccountDao accountDao;
    private final ClientDao clientDao;

    public AccountService(AccountDao accountDao, ClientDao clientDao) {
        this.accountDao = accountDao;
        this.clientDao = clientDao;
    }

    public List<Account> findAll() {
        return accountDao.findAll();
    }

    public Account findById(int accountId) {
        return accountDao.findById(accountId).orElseThrow(() -> new RuntimeException("account not found"));
    }

    public void transferMoney(TransactionRequestDto transactionRequestDto) {
        int accountIdFrom = transactionRequestDto.getAccountIdFrom();
        int accountIdTo = transactionRequestDto.getAccountIdTo();

        if (accountIdFrom != accountIdTo) {
            Account accountFrom = accountDao.findById(accountIdFrom).orElseThrow(() -> new RuntimeException("account not found"));
            Account accountTo = accountDao.findById(accountIdTo).orElseThrow(() -> new RuntimeException("account not found"));
            if (accountFrom.isOpen() && accountTo.isOpen()) {
                Client clientFrom = clientDao.findById(accountFrom.getClientId()).orElseThrow(() -> new RuntimeException("client not found"));
                List<Contractor> contractorList = clientFrom.getContractorsList();
                if (contractorList.isEmpty()) {
                    throw new RuntimeException("no contractors to transfer money");
                } else {
                    for (Contractor c : contractorList) {
                        if (c.getContractorId() == accountTo.getClientId()) {   //TODO FIXED LINE
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

        Optional<Account> optionalAccount = accountDao.findById(accountIdTo);
        if (optionalAccount.isPresent()) {
            Account account = optionalAccount.get();
            if (account.isOpen()) {
                BigDecimal balance = account.getBalance();
                balance = balance.add(value);
                accountDao.updateBalance(accountIdTo, balance);
            } else {
                log.info("Warning! Account is not opened!");
            }
        } else {
            log.info("Warning! Account does not exists!");
        }
    }

    public void addAccount(AccountRequestDto accountRequestDto) {
        String cardNumber = generateAccountNumber();
        BigDecimal balance = BigDecimal.ZERO;
        boolean isOpen = true;
        int clientId = accountRequestDto.getClientId();
        List<Card> cardList = new ArrayList<>();
        Account account = new Account(cardNumber, balance, isOpen, clientId, cardList);
        accountDao.save(account);
    }

    private static String generateAccountNumber() {
        StringBuilder stringBuilder = new StringBuilder();
        ThreadLocalRandom threadLocalRandom = ThreadLocalRandom.current(); //todo check method how works
        for (int i = 0; i < ACCOUNT_NUMBER_LENGTH; i++) {
            stringBuilder.append(threadLocalRandom.nextInt(CARD_DIGITS));
        }
        return stringBuilder.toString();
    }
}
