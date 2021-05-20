//package controller;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.node.ArrayNode;
//import com.fasterxml.jackson.databind.node.ObjectNode;
//import com.sun.net.httpserver.HttpExchange;
//import com.sun.net.httpserver.HttpHandler;
//import dto.CardOrderRequestDto;
//import entity.Account;
//import entity.CardType;
//import entity.PaySystem;
//import service.AccountService;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.io.OutputStream;
//import java.sql.SQLException;
//import java.util.List;
//import java.util.Map;
//
//public class TransactionsHandler implements HttpHandler {
//
//    private final AccountService accountService;
//
//    public TransactionsHandler(AccountService accountService) {
//        this.accountService = accountService;
//    }
//
//    @Override
//    public void handle(HttpExchange exchange) throws IOException { //todo class
//
//        InputStreamReader inputStreamReader = new InputStreamReader(exchange.getRequestBody());
//        BufferedReader br = new BufferedReader(inputStreamReader);
//        String body = br.readLine();
//        Map<String, String> map = requestBodyToJson(body);
//
//        CardType cardType = CardType.valueOf(map.get("cardType"));
//        PaySystem paySystem = PaySystem.valueOf(map.get("paySystem"));
//        int accountId = Integer.parseInt(map.get("accountId"));
//
//        CardOrderRequestDto cardOrderRequestDto = new CardOrderRequestDto(cardType, paySystem, accountId);
//
//        try {
//            cardService.orderCard(cardOrderRequestDto);
//        } catch (SQLException sqlException) {
//            sqlException.printStackTrace();
//        }
//
//        log.info("POST request with payload " + map);
//
//        String jsonResponse = map.toString();
//        exchange.sendResponseHeaders(200, jsonResponse.length());
//        OutputStream outputStream = exchange.getResponseBody();
//        outputStream.write(jsonResponse.getBytes());
//    }
//}
