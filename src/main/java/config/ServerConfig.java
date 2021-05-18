package config;

import com.sun.net.httpserver.HttpServer;
import controller.AccountHandler;
import controller.CardHandler;
import controller.UserHandler;
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

    public static final String FIND_ALL_USERS = "/users";
    public static final String FIND_ALL_ACCOUNTS = "/accounts";
    public static final String FIND_ALL_CARDS = "/cards";
    public static final String CARD_ORDER = "/cards/order";


    private static final Logger log = Logger.getLogger(ServerConfig.class.getName());

    public static void startServer(UserService userService, CardService cardService, AccountService accountService) throws IOException {
        HttpServer httpServer = HttpServer.create(new InetSocketAddress(PORT), BACKLOG);
        httpServer.createContext(FIND_ALL_USERS, new UserHandler(userService));
        httpServer.createContext(FIND_ALL_ACCOUNTS, new AccountHandler(accountService));

        httpServer.createContext(FIND_ALL_CARDS, new CardHandler(cardService));
        httpServer.createContext(CARD_ORDER, new CardHandler(cardService));


        ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);
        httpServer.setExecutor(threadPoolExecutor);
        httpServer.start();

        log.info("Server started on port 8000");
        log.info("---------------------------");

    }
}
