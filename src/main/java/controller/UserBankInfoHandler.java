package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import dto.UserDto;
import service.UserService;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

public class UserBankInfoHandler implements HttpHandler {

    private final UserService userService;

    public UserBankInfoHandler(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        ArrayNode arrayNode = objectMapper.createArrayNode();

        List<UserDto> userList = userService.findUsersWithCards();

        for (UserDto userDto : userList) {
            ObjectNode objectNode = objectMapper.createObjectNode();
            objectNode.put("firstName", userDto.getFirstName());
            objectNode.put("lastName", userDto.getLastName());
            objectNode.put("balance", userDto.getBalance());
            objectNode.put("number", userDto.getCardNumber());
            objectNode.put("cardType", userDto.getCardType().toString());
            objectNode.put("paySystem", userDto.getPaySystem().toString());
            arrayNode.add(objectNode);
        }
        String jsonResponse = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(arrayNode);
        exchange.sendResponseHeaders(200, jsonResponse.length());
        OutputStream outputStream = exchange.getResponseBody();
        outputStream.write(jsonResponse.getBytes());
    }
}