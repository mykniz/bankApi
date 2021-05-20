package controller;
import converters.*;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import dto.CardOrderRequestDto;
import entity.CardType;
import entity.PaySystem;
import service.CardService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.Map;
import java.util.logging.Logger;

public class CardOrderHandler implements HttpHandler {
    private static final Logger log = Logger.getLogger(GetCardsHandler.class.getName());
    private final CardService cardService;

    public CardOrderHandler(CardService cardService) {
        this.cardService = cardService;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        if (exchange.getRequestMethod().equalsIgnoreCase("GET")) {


            String query = exchange.getRequestURI().getQuery();
            String cardType = QueryToMapParser.queryToMap(query).get("cardType");
            String paySystem = QueryToMapParser.queryToMap(query).get("paySystem");
            int accountId = Integer.parseInt(QueryToMapParser.queryToMap(query).get("accountId"));

            CardOrderRequestDto cardOrderRequestDto = new CardOrderRequestDto(CardType.valueOf(cardType),
                    PaySystem.valueOf(paySystem), accountId);

            try {
                cardService.orderCard(cardOrderRequestDto);
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
            }

            log.info("GET request with payload " + query);

            String jsonResponse = query;
            exchange.sendResponseHeaders(200, jsonResponse.length());
            OutputStream outputStream = exchange.getResponseBody();
            outputStream.write(jsonResponse.getBytes());
        }
        if (exchange.getRequestMethod().equalsIgnoreCase("POST")) {
            //    Map<String, String> map = new ObjectMapper().readValue(exchange.getRequestBody(), Map.class);

            InputStreamReader inputStreamReader = new InputStreamReader(exchange.getRequestBody());
            BufferedReader br = new BufferedReader(inputStreamReader);
            String body = br.readLine();
            Map<String, String> map = RequestBodyToMapParser.requestBodyToMap(body);

            CardType cardType = CardType.valueOf(map.get("cardType"));
            PaySystem paySystem = PaySystem.valueOf(map.get("paySystem"));
            int accountId = Integer.parseInt(map.get("accountId"));

            CardOrderRequestDto cardOrderRequestDto = new CardOrderRequestDto(cardType, paySystem, accountId);

            try {
                cardService.orderCard(cardOrderRequestDto);
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
            }

            log.info("POST request with payload " + map);

            String jsonResponse = map.toString();
            exchange.sendResponseHeaders(200, jsonResponse.length());
            OutputStream outputStream = exchange.getResponseBody();
            outputStream.write(jsonResponse.getBytes());
        }
    }
}
