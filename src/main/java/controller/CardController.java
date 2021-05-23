package controller;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import config.ServerConfig;
import converter.HttpExchangeToMapParser;
import converter.JsonResponseParser;
import dto.CardOrderRequestDto;
import entity.Card;
import entity.CardType;
import entity.PaySystem;
import service.CardService;
import java.util.List;
import java.util.Map;

public class CardController implements HttpHandler {

    private static final String ORDER_CARD = "/card/orderCard";
    private final CardService cardService;

    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @Override
    public void handle(HttpExchange exchange) {
        String method = exchange.getRequestMethod();
        String path = exchange.getRequestURI().getPath();
        switch (method) {
            case ServerConfig.GET:

            case ServerConfig.POST:
                switch (path) {
                    case ORDER_CARD:
                        orderCardHandler(exchange);
                        break;
                }
        }
    }

    /**
     * Receives card dto as JSON from POST method and return JSON array of cards
     *
     * @param exchange
     */
    private void orderCardHandler(HttpExchange exchange) {
        Map<String, String> map = HttpExchangeToMapParser.httpExchangeToMap(exchange);
        CardType cardType = CardType.valueOf(map.get("cardType"));
        PaySystem paySystem = PaySystem.valueOf(map.get("paySystem"));
        int accountId = Integer.parseInt(map.get("accountId"));
        CardOrderRequestDto cardOrderRequestDto = new CardOrderRequestDto(cardType, paySystem, accountId);
        cardService.orderCard(cardOrderRequestDto);
        List<Card> cardsListForAccount = cardService.findCardsByAccountId(accountId);
        JsonResponseParser.toJsonResponse(exchange, cardsListForAccount);
    }
}
