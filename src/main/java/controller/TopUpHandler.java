package controller;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import converters.*;
import dto.TopUpRequestDto;
import entity.Account;
import service.AccountService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.Map;
import java.util.logging.Logger;

public class TopUpHandler implements HttpHandler {
    private static final Logger log = Logger.getLogger(TopUpHandler.class.getName());
    private final AccountService accountService;

    public TopUpHandler(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {

        InputStreamReader inputStreamReader = new InputStreamReader(exchange.getRequestBody());
        BufferedReader br = new BufferedReader(inputStreamReader);
        String body = br.readLine();
        Map<String, String> map = RequestBodyToMapParser.requestBodyToMap(body);

        int accountId = Integer.parseInt(map.get("accountId"));
        BigDecimal value = BigDecimal.valueOf(Long.parseLong(map.get("value")));

        TopUpRequestDto topUpRequestDto = new TopUpRequestDto(accountId,value);

        Account account = null;

        try {
            accountService.topUp(topUpRequestDto);
            account = accountService.findById(accountId);
        } catch (Exception e) {
            e.printStackTrace();
        }

        log.info(account.toString());

        String jsonResponse = map.toString();
        exchange.sendResponseHeaders(200, jsonResponse.length());
        OutputStream outputStream = exchange.getResponseBody();
        outputStream.write(jsonResponse.getBytes());
    }
}
