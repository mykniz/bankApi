package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import config.ServerConfig;
import converters.*;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import dto.CardOrderRequestDto;
import entity.Card;
import entity.CardType;
import entity.PaySystem;
import service.CardService;

import java.io.*;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class CardOrderHandler implements HttpHandler {
    private static final Logger log = Logger.getLogger(CardOrderHandler.class.getName());
    private final CardService cardService;

    public CardOrderHandler(CardService cardService) {
        this.cardService = cardService;
    }

    @Override
    public void handle(HttpExchange exchange) {

        /**
         * realisation for GET request with params from URI
         */
        if (exchange.getRequestMethod().equalsIgnoreCase("GET")) {

            try {
                String query = exchange.getRequestURI().getQuery();
                String cardType = QueryToMapParser.queryToMap(query).get("cardType");
                String paySystem = QueryToMapParser.queryToMap(query).get("paySystem");
                int accountId = Integer.parseInt(QueryToMapParser.queryToMap(query).get("accountId"));

                CardOrderRequestDto cardOrderRequestDto = new CardOrderRequestDto(CardType.valueOf(cardType),
                        PaySystem.valueOf(paySystem), accountId);
                cardService.orderCard(cardOrderRequestDto);

                log.info("GET request with payload " + query);

                String jsonResponse = query;
                exchange.sendResponseHeaders(ServerConfig.STATUS_OK, jsonResponse.length());
                OutputStream outputStream = exchange.getResponseBody();
                outputStream.write(jsonResponse.getBytes());
            } catch (SQLException | IOException exception) {
                exception.printStackTrace();
            }
        }

        /**
         * realisation for POST request with params from Request Body with out jSON MAPPER
         */
        if (exchange.getRequestMethod().equalsIgnoreCase("POST")) {
            ObjectMapper objectMapper = new ObjectMapper();

            try {
                InputStreamReader inputStreamReader = new InputStreamReader(exchange.getRequestBody());
                BufferedReader br = new BufferedReader(inputStreamReader);
                String body = br.readLine();
                Map<String, String> map = RequestBodyToMapParser.requestBodyToMap(body);

                CardType cardType = CardType.valueOf(map.get("cardType"));
                PaySystem paySystem = PaySystem.valueOf(map.get("paySystem"));
                int accountId = Integer.parseInt(map.get("accountId"));
                CardOrderRequestDto cardOrderRequestDto = new CardOrderRequestDto(cardType, paySystem, accountId);

                cardService.orderCard(cardOrderRequestDto);
                List<Card> cardsListForAccount = cardService.findCardsByAccountId(accountId);

                String jsonResponse = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(cardsListForAccount);
                exchange.sendResponseHeaders(ServerConfig.STATUS_OK, jsonResponse.length());
                OutputStream outputStream = exchange.getResponseBody();
                outputStream.write(jsonResponse.getBytes());
                log.info("POST request with payload " + map);

            } catch (SQLException | IOException exception) {
                exception.printStackTrace();
            }
        }
    }

}
