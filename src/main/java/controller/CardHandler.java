package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import config.DatabaseConfig;
import config.ServerConfig;
import entity.Card;
import entity.User;
import service.CardService;

import java.io.*;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class CardHandler implements HttpHandler {

    private static final int STATUS_OK = 200;
    private static final String METHOD_GET = "GET";
    private static final String METHOD_POST = "POST";
    private final CardService cardService;
    private static final Logger log = Logger.getLogger(CardHandler.class.getName());


    public CardHandler(CardService cardService) {
        this.cardService = cardService;
    }


    @Override
    public void handle(HttpExchange exchange) throws IOException {

        final String URI = exchange.getRequestURI().toString();

        switch (URI) {
            case ServerConfig.FIND_ALL_CARDS:
                    findAllCards(exchange);
            case ServerConfig.CARD_ORDER:
                orderCard(exchange);
        }
    }


//    private static Map<String, String> queryToMap(String query){
//        Map<String, String> result = new HashMap<String, String>();
//        for (String param : query.split("&")) {
//            String pair[] = param.split("=");
//            if (pair.length>1) {
//                result.put(pair[0], pair[1]);
//            }else{
//                result.put(pair[0], "");
//            }
//        }
//        return result;
//    }

    private void orderCard(HttpExchange exchange) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        String s = exchange.getRequestURI().getQuery();
        log.info(s);



    }


    private void findAllCards(HttpExchange exchange) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        ArrayNode arrayNode = objectMapper.createArrayNode();
        try {
            List<Card> cardList = cardService.findAll();
            for (Card card : cardList) {
                ObjectNode objectNode = objectMapper.createObjectNode();
                objectNode.put("number", card.getNumber());
                objectNode.put("balance", card.getBalance());
                objectNode.put("cardType", card.getCardType().toString());
                objectNode.put("paySystem", card.getPaySystem().toString());
                arrayNode.add(objectNode);
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        String jsonResponse = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(arrayNode);
        exchange.sendResponseHeaders(STATUS_OK, jsonResponse.length());
        OutputStream outputStream = exchange.getResponseBody();
        outputStream.write(jsonResponse.getBytes());
    }
}
