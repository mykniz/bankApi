package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import config.ServerConfig;
import entity.Card;
import service.CardService;
import java.io.*;
import java.sql.SQLException;
import java.util.List;

public class GetCardsHandler implements HttpHandler {

    private final CardService cardService;
    public GetCardsHandler(CardService cardService) {
        this.cardService = cardService;
    }

    @Override
    public void handle(HttpExchange exchange) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            List<Card> cardList = cardService.findAll();
            String jsonResponse = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(cardList);
            exchange.sendResponseHeaders(ServerConfig.STATUS_OK, jsonResponse.length());
            OutputStream outputStream = exchange.getResponseBody();
            outputStream.write(jsonResponse.getBytes());
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
