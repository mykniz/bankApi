package controller;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import config.ServerConfig;
import converter.HttpExchangeToMapParser;
import converter.JsonResponseParser;
import dto.AccountRequestDto;
import dto.ClientBankInfoResponseDto;
import dto.ClientRequestDto;
import entity.Account;
import entity.Card;
import entity.Client;
import service.AccountService;
import service.CardService;
import service.ClientService;
import java.util.List;
import java.util.Map;

public class AuthHandler implements HttpHandler {

    private static final String ADD_CLIENT = "/admin/addClient";
    private static final String ADD_ACCOUNT = "/admin/addAccount";
    private static final String CARD_ORDER_APPROVE = "/admin/cardOderApprove";
    private static final String TRANSACTION_APPROVE = "/admin/transactionApprove";

    private static final String FIND_ALL_CLIENTS = "/admin/clients";
    private static final String FIND_ALL_CLIENTS_BANK_INFO = "/admin/clients/bankInfo";
    private static final String FIND_ALL_ACCOUNTS = "/admin/accounts";
    private static final String FIND_ALL_CARDS = "/admin/cards";

    private final ClientService clientService;
    private final AccountService accountService;
    private final CardService cardService;


    public AuthHandler(ClientService clientService, AccountService accountService, CardService cardService) {
        this.clientService = clientService;
        this.accountService = accountService;
        this.cardService = cardService;
    }

    @Override
    public void handle(HttpExchange exchange) {

        String method = exchange.getRequestMethod();
        String path = exchange.getRequestURI().getPath();
        switch (method) {
            case ServerConfig.GET:
                switch (path) {
                    case FIND_ALL_CLIENTS:
                        getClientsHandler(exchange);
                        break;
                    case FIND_ALL_CLIENTS_BANK_INFO:
                        getClientBankInfoHandler(exchange);
                        break;
                    case FIND_ALL_ACCOUNTS:
                        getAccountsHandler(exchange);
                        break;
                    case FIND_ALL_CARDS:
                        getCardsHandler(exchange);
                        break;
                }
            case ServerConfig.POST:
                switch (path) {
                    case ADD_CLIENT:
                        addClientHandler(exchange);
                        break;
                    case ADD_ACCOUNT:
                        addAccountHandler(exchange);
                        break;
                    case CARD_ORDER_APPROVE:
                        cardOderApproveHandler(exchange);
                        break;
                    case TRANSACTION_APPROVE:
                        //    transactionApprove(exchange);
                }
        }
    }

    private void addClientHandler(HttpExchange exchange) {
        Map<String, String> map = HttpExchangeToMapParser.httpExchangeToMap(exchange);
        String firstName = map.get("firstName");
        String lastName = map.get("lastName");
        String phoneNumber = map.get("phoneNumber");
        ClientRequestDto clientRequestDto = new ClientRequestDto(firstName, lastName, phoneNumber);
        clientService.addClient(clientRequestDto);
        List<Client> clientList = clientService.findAll();
        JsonResponseParser.toJsonResponse(exchange, clientList);
    }

    private void addAccountHandler(HttpExchange exchange) {

        Map<String, String> map = HttpExchangeToMapParser.httpExchangeToMap(exchange);
        int clientId = Integer.parseInt(map.get("clientId"));
        AccountRequestDto accountRequestDto = new AccountRequestDto(clientId);
        accountService.addAccount(accountRequestDto);
        List<Account> accountList = accountService.findAll();
        JsonResponseParser.toJsonResponse(exchange, accountList);
    }

    private void cardOderApproveHandler(HttpExchange exchange) {
        Map<String, String> map = HttpExchangeToMapParser.httpExchangeToMap(exchange);
        String number = map.get("number");
        String isActive = map.get("isActive");
        cardService.changeStatus(number, isActive);
        List<Card> cardList = cardService.findAll();
        JsonResponseParser.toJsonResponse(exchange, cardList);
    }


    private void getClientsHandler(HttpExchange exchange) {
        List<Client> clientList = clientService.findAll();
        JsonResponseParser.toJsonResponse(exchange, clientList);
    }

    private void getClientBankInfoHandler(HttpExchange exchange) {
        List<ClientBankInfoResponseDto> usersBankInfo = clientService.findClientsBankInfo();
        JsonResponseParser.toJsonResponse(exchange, usersBankInfo);
    }

    private void getAccountsHandler(HttpExchange exchange) {
        List<Account> accountList = accountService.findAll();
        JsonResponseParser.toJsonResponse(exchange, accountList);
    }

    private void getCardsHandler(HttpExchange exchange) {
        List<Card> cardList = cardService.findAll();
        JsonResponseParser.toJsonResponse(exchange, cardList);
    }
}
