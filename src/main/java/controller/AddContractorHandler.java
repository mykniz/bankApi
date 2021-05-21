package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import converters.*;
import dto.AddContractorRequestDto;
import entity.User;
import service.UserService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class AddContractorHandler implements HttpHandler {
    private static final Logger log = Logger.getLogger(BalanceCheckHandler.class.getName());
    private final UserService userService;

    public AddContractorHandler(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            InputStreamReader inputStreamReader = new InputStreamReader(exchange.getRequestBody());
            BufferedReader br = new BufferedReader(inputStreamReader);
            String body = br.readLine();
            Map<String, String> map = RequestBodyToMapParser.requestBodyToMap(body);

            int userId = Integer.parseInt(map.get("userId"));
            int contractorId = Integer.parseInt(map.get("contractorId"));

            AddContractorRequestDto addContractorRequestDto = new AddContractorRequestDto(userId, contractorId);

            userService.addContractor(addContractorRequestDto);
            List<User> userList = userService.findAll();

            String jsonResponse = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(userList);
            exchange.sendResponseHeaders(200, jsonResponse.length());
            OutputStream outputStream = exchange.getResponseBody();
            outputStream.write(jsonResponse.getBytes());
        } catch (SQLException | IOException exception) {
            exception.printStackTrace();
        }
    }

}

