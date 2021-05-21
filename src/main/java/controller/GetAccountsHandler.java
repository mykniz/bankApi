package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import config.ServerConfig;
import entity.Account;
import org.h2.util.json.JSONArray;
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

    @Override
    public void handle(HttpExchange exchange) { //todo make JSON serializer
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            List<Account> accountList = accountService.findAll();
            String jsonResponse = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(accountList);
            exchange.sendResponseHeaders(ServerConfig.STATUS_OK, jsonResponse.length());
            OutputStream outputStream = exchange.getResponseBody();
            outputStream.write(jsonResponse.getBytes());
        } catch (SQLException | IOException exception) {
            exception.printStackTrace();
        }
    }
}
