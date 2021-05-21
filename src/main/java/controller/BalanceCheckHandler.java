package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import config.ServerConfig;
import converter.*;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import entity.Account;
import service.AccountService;

import java.io.IOException;
import java.io.OutputStream;
import java.util.logging.Logger;

public class BalanceCheckHandler implements HttpHandler {

    private static final Logger log = Logger.getLogger(BalanceCheckHandler.class.getName());
    private final AccountService accountService;

    public BalanceCheckHandler(AccountService accountService) {
        this.accountService = accountService;
    }

    /**
     * Receives account id as JSON from POST method and return JSON account
     * @param exchange
     * @throws IOException
     */
    @Override
    public void handle(HttpExchange exchange) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        String query = exchange.getRequestURI().getQuery();
        int accountId = Integer.parseInt(QueryToMapParser.queryToMap(query).get("accountId"));
        Account account = accountService.findById(accountId);

        String jsonResponse = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(account);

        exchange.sendResponseHeaders(ServerConfig.STATUS_OK, jsonResponse.length());
        OutputStream outputStream = exchange.getResponseBody();
        outputStream.write(jsonResponse.getBytes());
        exchange.close();

    }
}
