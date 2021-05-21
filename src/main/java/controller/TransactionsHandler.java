package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import converter.*;
import dto.TransactionRequestDto;
import entity.Account;
import service.AccountService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class TransactionsHandler implements HttpHandler {

    private final AccountService accountService;

    public TransactionsHandler(AccountService accountService) {
        this.accountService = accountService;
    }

    /**
     * Receives transaction dto as JSON from POST method and return JSON array of accounts
     * @param exchange
     */
    @Override
    public void handle(HttpExchange exchange) { //todo class

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            InputStreamReader inputStreamReader = new InputStreamReader(exchange.getRequestBody());
            BufferedReader br = new BufferedReader(inputStreamReader);
            String body = br.readLine();
            Map<String, String> map = RequestBodyToMapParser.requestBodyToMap(body);

            int accountIdFrom = Integer.parseInt(map.get("accountIdFrom"));
            int accountIdTo = Integer.parseInt(map.get("accountIdTo"));
            BigDecimal value = BigDecimal.valueOf(Long.parseLong(map.get("value")));

            TransactionRequestDto transactionRequestDto = new TransactionRequestDto(accountIdFrom, accountIdTo, value);
            accountService.transferMoney(transactionRequestDto);
            List<Account> accountList = accountService.findAll();

            String jsonResponse = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(accountList);
            exchange.sendResponseHeaders(200, jsonResponse.length());
            OutputStream outputStream = exchange.getResponseBody();
            outputStream.write(jsonResponse.getBytes());
            exchange.close();
        } catch (SQLException | IOException exception) {
            exception.printStackTrace();
        }
    }
}
