package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import config.ServerConfig;
import entity.Account;
import service.AccountService;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.List;

public class GetAccountsHandler implements HttpHandler {

    private final AccountService accountService;

    public GetAccountsHandler(AccountService accountService) {
        this.accountService = accountService;
    }

    /**
     * Receives GET request ant /accounts and return JSON array of accounts
     * @param exchange
     */
    @Override
    public void handle(HttpExchange exchange) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            List<Account> accountList = accountService.findAll();
            String jsonResponse = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(accountList);
            exchange.sendResponseHeaders(ServerConfig.STATUS_OK, jsonResponse.length());
            OutputStream outputStream = exchange.getResponseBody();
            outputStream.write(jsonResponse.getBytes());
            exchange.close();
        } catch (SQLException | IOException exception) {
            exception.printStackTrace();
        }
    }
}
