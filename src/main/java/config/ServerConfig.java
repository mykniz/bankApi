package config;

import com.sun.net.httpserver.HttpServer;
import controller.*;
import service.AccountService;
import service.CardService;
import service.UserService;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.logging.Logger;

public class ServerConfig {

    private static final int PORT = 8000;
    private static final int BACKLOG = 1;
    public static final int STATUS_OK = 200;

    private static final String FIND_ALL_USERS = "/users";
    private static final String FIND_ALL_USERS_BANK_INFO = "/users/bankInfo";
    private static final String ADD_CONTRACTOR = "/users/addContractor";
    private static final String FIND_ALL_ACCOUNTS = "/accounts";
    private static final String BALANCE_TOP_UP = "/accounts/topUp";
    private static final String BALANCE_CHECK = "/accounts/check";
    private static final String TRANSACTIONS = "/accounts/transactions";
    private static final String FIND_ALL_CARDS = "/cards";
    private static final String CARD_ORDER = "/cards/order";


    private static final Logger log = Logger.getLogger(ServerConfig.class.getName());

    public static void startServer(UserService userService, CardService cardService, AccountService accountService) throws IOException {
        HttpServer httpServer = HttpServer.create(new InetSocketAddress(PORT), BACKLOG);

        httpServer.createContext(FIND_ALL_USERS, new GetUsersHandler(userService));
        httpServer.createContext(FIND_ALL_USERS_BANK_INFO, new UserBankInfoHandler(userService));
        httpServer.createContext(ADD_CONTRACTOR, new AddContractorHandler(userService));
        httpServer.createContext(FIND_ALL_ACCOUNTS, new GetAccountsHandler(accountService));
        httpServer.createContext(BALANCE_TOP_UP, new TopUpHandler(accountService));
        httpServer.createContext(BALANCE_CHECK, new BalanceCheckHandler(accountService));
        httpServer.createContext(TRANSACTIONS, new TransactionsHandler(accountService));
        httpServer.createContext(FIND_ALL_CARDS, new GetCardsHandler(cardService));
        httpServer.createContext(CARD_ORDER, new CardOrderHandler(cardService));

        ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);
        httpServer.setExecutor(threadPoolExecutor);
        httpServer.start();

        log.info("Server started on port 8000");
        log.info("---------------------------");
    }
}
