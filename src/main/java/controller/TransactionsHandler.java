package controller;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import converter.HttpExchangeToMapParser;
import converter.JsonResponseParser;
import dto.TransactionRequestDto;
import entity.Account;
import service.AccountService;
import java.math.BigDecimal;
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
