package controller;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import dto.CardDto;
import entity.Card;
import entity.CardType;
import entity.PaySystem;
import service.CardService;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class CardOrderHandler implements HttpHandler {


    private static final Logger log = Logger.getLogger(GetCardsHandler.class.getName());
    private static final int STATUS_OK = 200;
    private final CardService cardService;

    public CardOrderHandler(CardService cardService) {
        this.cardService = cardService;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {

        String query = exchange.getRequestURI().getQuery();
        int accountId = Integer.parseInt(queryToMap(query).get("accountId"));
        String cardType = queryToMap(query).get("cardType");
        String paySystem = queryToMap(query).get("paySystem");

        try {
            cardService.orderCard(new CardDto(CardType.valueOf(cardType),
                    PaySystem.valueOf(paySystem),  accountId));
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

        String res = "accountId= " + accountId + " cardType = " + cardType + " paySystem + " + paySystem;
        exchange.sendResponseHeaders(STATUS_OK, res.length());
        OutputStream outputStream = exchange.getResponseBody();
        outputStream.write(res.getBytes());
    }

    private static Map<String, String> queryToMap(String query) {
        Map<String, String> result = new HashMap<String, String>();
        for (String param : query.split("&")) {
            String pair[] = param.split("=");
            if (pair.length > 1) {
                result.put(pair[0], pair[1]);
            } else {
                result.put(pair[0], "");
            }
        }
        return result;
    }
}
