package controller;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import config.ServerConfig;
import converter.HttpExchangeToMapParser;
import converter.JsonResponseParser;
import dto.AddContractorRequestDto;
import entity.Client;
import service.ClientService;
import java.util.List;
import java.util.Map;

public class ClientController implements HttpHandler {

    private static final String ADD_CONTRACTOR = "/client/addContractor";
    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @Override
    public void handle(HttpExchange exchange) {
        String method = exchange.getRequestMethod();
        String path = exchange.getRequestURI().getPath();
        switch (method) {
            case ServerConfig.GET:  //todo

            case ServerConfig.POST:
                switch (path) {
                    case ADD_CONTRACTOR:
                        addContractorHandler(exchange);
                        break;
                }
        }
    }

    private void addContractorHandler(HttpExchange exchange) {
        Map<String, String> map = HttpExchangeToMapParser.httpExchangeToMap(exchange);
        int clientId = Integer.parseInt(map.get("clientId"));
        int contractorId = Integer.parseInt(map.get("contractorId"));
        AddContractorRequestDto addContractorRequestDto = new AddContractorRequestDto(clientId, contractorId);
        clientService.addContractor(addContractorRequestDto);
        List<Client> clientList = clientService.findAll();
      //  JsonResponseParser.toJsonResponse(exchange, "test");
        JsonResponseParser.toJsonResponse(exchange, clientList);
    }

}
