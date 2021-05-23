package config;

import com.sun.net.httpserver.*;
import controller.*;
import service.AccountService;
import service.CardService;
import service.ClientService;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.logging.Logger;

/**
 * basic server configuration
 */
public class ServerConfig {

    private static final Logger log = Logger.getLogger(ServerConfig.class.getName());

    private static final int PORT = 8000;
    private static final int BACKLOG = 1;
    public static final int STATUS_OK = 200;
    public static final String GET = "GET";
    public static final String POST = "POST";
    private static final String ADMIN = "/admin";
    private static final String CLIENT = "/client";
    private static final String ACCOUNT = "/account";
    private static final String CARD = "/card";

    /**
     * start server on port 8000
     *
     * @param clientService service between http and database for clients operations
     * @param cardService service between http and database for cards operations
     * @param accountService service between http and database for accounts operations
     * @throws IOException
     */
    public static void startServer(ClientService clientService,
                                   CardService cardService,
                                   AccountService accountService) throws IOException {
        HttpServer httpServer = HttpServer.create(new InetSocketAddress(PORT), BACKLOG);

        HttpContext authContext = httpServer.createContext(ADMIN, new AdminController(clientService,accountService,cardService));
        authContext.setAuthenticator(new AuthenticationConfig("auth"));

        httpServer.createContext(CLIENT, new ClientController(clientService));
        httpServer.createContext(ACCOUNT, new AccountController(accountService));
        httpServer.createContext(CARD, new CardController(cardService));

        ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);
        httpServer.setExecutor(threadPoolExecutor);
        httpServer.start();

        log.info("Server started on port 8000");
        log.info("---------------------------");
    }
}
