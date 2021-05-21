package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import config.ServerConfig;
import entity.Card;
import service.CardService;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

public class GetCardsHandler implements HttpHandler {

    private final CardService cardService;

    public GetCardsHandler(CardService cardService) {
        this.cardService = cardService;
    }

    /**
     * Receives GET request ant /cards and return JSON array of cards
     * @param exchange
     */
    @Override
    public void handle(HttpExchange exchange) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            List<Card> cardList = cardService.findAll();
            String jsonResponse = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(cardList);
            exchange.sendResponseHeaders(ServerConfig.STATUS_OK, jsonResponse.length());
            OutputStream outputStream = exchange.getResponseBody();
            outputStream.write(jsonResponse.getBytes());
            exchange.close();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
