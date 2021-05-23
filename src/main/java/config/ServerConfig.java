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

    private static final int PORT = 8000;
    private static final int BACKLOG = 1;
    public static final int STATUS_OK = 200;
    public static final String GET = "GET";
    public static final String POST = "POST";

    private static final String ADD_CONTRACTOR = "/clients/addContractor";
    private static final String BALANCE_TOP_UP = "/accounts/topUpBalance";
    private static final String BALANCE_CHECK = "/accounts/checkBalance";
    private static final String TRANSACTIONS = "/accounts/transactions";
    private static final String CARD_ORDER = "/cards/order";
    private static final Logger log = Logger.getLogger(ServerConfig.class.getName());


    /**
     * start server on port 8000
     *
     * @param clientService
     * @param cardService
     * @param accountService
     * @throws IOException
     */
    public static void startServer(ClientService clientService,
                                   CardService cardService,
                                   AccountService accountService) throws IOException {
        HttpServer httpServer = HttpServer.create(new InetSocketAddress(PORT), BACKLOG);

        HttpContext authContext = httpServer.createContext("/admin", new AuthHandler(clientService,accountService,cardService));
        authContext.setAuthenticator(new AuthenticationConfig("auth"));

        httpServer.createContext(ADD_CONTRACTOR, new AddContractorHandler(clientService));
        httpServer.createContext(BALANCE_TOP_UP, new TopUpHandler(accountService));
        httpServer.createContext(BALANCE_CHECK, new BalanceCheckHandler(accountService));
        httpServer.createContext(TRANSACTIONS, new TransactionsHandler(accountService));
        httpServer.createContext(CARD_ORDER, new CardOrderHandler(cardService));

        ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);
        httpServer.setExecutor(threadPoolExecutor);
        httpServer.start();

        log.info("Server started on port 8000");
        log.info("---------------------------");
    }
}
