package controller;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import config.ServerConfig;
import converter.HttpExchangeToMapParser;
import converter.JsonResponseParser;
import converter.QueryToMapParser;
import dto.TransactionRequestDto;
import entity.Account;
import service.AccountService;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class AccountController implements HttpHandler {

    private static final String TOP_UP_BALANCE = "/account/topUpBalance";
    private static final String CHECK_BALANCE = "/account/checkBalance";
    private static final String TRANSACTIONS = "/account/transactions";
    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public void handle(HttpExchange exchange) {
        String method = exchange.getRequestMethod();
        String path = exchange.getRequestURI().getPath();
        switch (method) {
            case ServerConfig.GET:
                switch (path) {
                    case CHECK_BALANCE:
                        checkBalance(exchange);
                        break;
                }
            case ServerConfig.POST:
                switch (path) {
                    case TOP_UP_BALANCE:
                        topUpHandler(exchange);
                        break;
                    case TRANSACTIONS:
                        transactionsHandler(exchange);
                }
        }
    }


    /**
     * Receives topUp dto as JSON from POST method and return account as JSON
     *
     * @param exchange
     */
    private void topUpHandler(HttpExchange exchange) {
        Map<String, String> map = HttpExchangeToMapParser.httpExchangeToMap(exchange);
        int accountId = Integer.parseInt(map.get("accountId"));
        BigDecimal value = BigDecimal.valueOf(Long.parseLong(map.get("value")));
        accountService.topUp(accountId, value);
        Account account = accountService.findById(accountId);
        JsonResponseParser.toJsonResponse(exchange, account);
    }


    /**
     * Receives account id as query parameter from GET method and return JSON account
     */
    private void checkBalance(HttpExchange exchange) {
        String query = exchange.getRequestURI().getQuery();
        int accountId = Integer.parseInt(QueryToMapParser.queryToMap(query).get("accountId"));
        Account account = accountService.findById(accountId);
        JsonResponseParser.toJsonResponse(exchange, account.getBalance());
    }

    /**
     * Receives transaction dto as JSON from POST method and return JSON array of accounts
     *
     * @param exchange
     */
    private void transactionsHandler(HttpExchange exchange) {
        Map<String, String> map = HttpExchangeToMapParser.httpExchangeToMap(exchange);
        int accountIdFrom = Integer.parseInt(map.get("accountIdFrom"));
        int accountIdTo = Integer.parseInt(map.get("accountIdTo"));
        BigDecimal value = BigDecimal.valueOf(Long.parseLong(map.get("value")));
        TransactionRequestDto transactionRequestDto = new TransactionRequestDto(accountIdFrom, accountIdTo, value);
        accountService.transferMoney(transactionRequestDto);
        List<Account> accountList = accountService.findAll();
        JsonResponseParser.toJsonResponse(exchange, accountList);
    }

}
