package controller;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import converter.HttpExchangeToMapParser;
import converter.JsonResponseParser;
import dto.CardOrderRequestDto;
import entity.Card;
import entity.CardType;
import entity.PaySystem;
import service.CardService;
import java.util.List;
import java.util.Map;

public class CardOrderHandler implements HttpHandler {
    private final CardService cardService;

    public CardOrderHandler(CardService cardService) {
        this.cardService = cardService;
    }

    /**
     * Receives card dto as JSON from POST method and return JSON array of cards
     *
     * @param exchange
     */
    @Override
    public void handle(HttpExchange exchange) {

        if (exchange.getRequestMethod().equalsIgnoreCase("POST")) {
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

}
