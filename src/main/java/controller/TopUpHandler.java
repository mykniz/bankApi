package controller;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import converter.HttpExchangeToMapParser;
import converter.JsonResponseParser;
import entity.Account;
import service.AccountService;
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
        Map<String, String> map = HttpExchangeToMapParser.httpExchangeToMap(exchange);
        int accountId = Integer.parseInt(map.get("accountId"));
        BigDecimal value = BigDecimal.valueOf(Long.parseLong(map.get("value")));
        accountService.topUp(accountId, value);
        Account account = accountService.findById(accountId);
        JsonResponseParser.toJsonResponse(exchange,account);
    }
}
