package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import entity.Card;
import service.CardService;

import java.io.*;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

public class GetCardsHandler implements HttpHandler {

    private static final int STATUS_OK = 200;
    private static final String METHOD_GET = "GET";
    private static final String METHOD_POST = "POST";
    private final CardService cardService;
    private static final Logger log = Logger.getLogger(GetCardsHandler.class.getName());


    public GetCardsHandler(CardService cardService) {
        this.cardService = cardService;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        ArrayNode arrayNode = objectMapper.createArrayNode();
        try {
            List<Card> cardList = cardService.findAll();
            for (Card card : cardList) {
                ObjectNode objectNode = objectMapper.createObjectNode();
                objectNode.put("number", card.getCardNumber());
                objectNode.put("cardType", card.getCardType().toString());
                objectNode.put("paySystem", card.getPaySystem().toString());
                objectNode.put("isActive", card.isActive());
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
