package controller;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import converter.HttpExchangeToMapParser;
import converter.JsonResponseParser;
import dto.AddContractorRequestDto;
import entity.Client;
import service.ClientService;
import java.util.List;
import java.util.Map;

public class AddContractorHandler implements HttpHandler {
    private final ClientService clientService;

    public AddContractorHandler(ClientService clientService) {
        this.clientService = clientService;
    }

    /**
     * Receives contractor JSON from POST method and return JSON array of users
     * @param exchange
     */
    @Override
    public void handle(HttpExchange exchange) {
        Map<String, String> map = HttpExchangeToMapParser.httpExchangeToMap(exchange);
        int clientId = Integer.parseInt(map.get("clientId"));
        int contractorId = Integer.parseInt(map.get("contractorId"));
        AddContractorRequestDto addContractorRequestDto = new AddContractorRequestDto(clientId, contractorId);
        clientService.addContractor(addContractorRequestDto);
        List<Client> clientList = clientService.findAll();
        JsonResponseParser.toJsonResponse(exchange, clientList);
    }

}

