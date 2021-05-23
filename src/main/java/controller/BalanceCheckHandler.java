package controller;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import converter.JsonResponseParser;
import converter.QueryToMapParser;
import entity.Account;
import service.AccountService;

public class BalanceCheckHandler implements HttpHandler {
    private final AccountService accountService;

    public BalanceCheckHandler(AccountService accountService) {
        this.accountService = accountService;
    }

    /**
     * Receives account id as JSON from POST method and return JSON account
     */
    @Override
    public void handle(HttpExchange exchange){
        String query = exchange.getRequestURI().getQuery();
        int accountId = Integer.parseInt(QueryToMapParser.queryToMap(query).get("accountId"));
        Account account = accountService.findById(accountId);
        JsonResponseParser.toJsonResponse(exchange, account);
    }
}
