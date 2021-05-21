package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import config.ServerConfig;
import converter.*;
import entity.Account;
import service.AccountService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.Map;

public class TopUpHandler implements HttpHandler {
    private final AccountService accountService;

    public TopUpHandler(AccountService accountService) {
        this.accountService = accountService;
    }

    /**
     * Receives topUp dto as JSON from POST method and return account as JSON
     * @param exchange
     */
    @Override
    public void handle(HttpExchange exchange) {

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            InputStreamReader inputStreamReader = new InputStreamReader(exchange.getRequestBody());
            BufferedReader br = new BufferedReader(inputStreamReader);
            String body = br.readLine();
            Map<String, String> map = RequestBodyToMapParser.requestBodyToMap(body);

            int accountId = Integer.parseInt(map.get("accountId"));
            BigDecimal value = BigDecimal.valueOf(Long.parseLong(map.get("value")));
            accountService.topUp(accountId, value);
            Account account = accountService.findById(accountId);

            String jsonResponse = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(account);
            exchange.sendResponseHeaders(ServerConfig.STATUS_OK, jsonResponse.length());
            OutputStream outputStream = exchange.getResponseBody();
            outputStream.write(jsonResponse.getBytes());
            exchange.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
