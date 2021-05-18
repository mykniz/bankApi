package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import entity.Account;
import entity.Card;
import service.AccountService;
import service.CardService;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.List;

public class AccountHandler implements HttpHandler {

    private final AccountService accountService;

    public AccountHandler(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        ArrayNode arrayNode = objectMapper.createArrayNode();

        try {
            List<Account> accountList = accountService.findAll();
            for (Account account : accountList) {
                ObjectNode objectNode = objectMapper.createObjectNode();
                objectNode.put("account", account.getAccount());
                objectNode.put("balance", account.getBalance());
                objectNode.put("isOpen", account.isOpen());
                arrayNode.add(objectNode);
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

        String jsonResponse = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(arrayNode);
        exchange.sendResponseHeaders(200, jsonResponse.length());
        OutputStream outputStream = exchange.getResponseBody();
        outputStream.write(jsonResponse.getBytes());
    }
}
