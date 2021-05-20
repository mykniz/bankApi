package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import dto.UserBankInfoResponseDto;
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

        List<UserBankInfoResponseDto> userList = userService.findUsersWithCards();

        for (UserBankInfoResponseDto userBankInfoResponseDto : userList) {
            ObjectNode objectNode = objectMapper.createObjectNode();
            objectNode.put("firstName", userBankInfoResponseDto.getFirstName());
            objectNode.put("lastName", userBankInfoResponseDto.getLastName());
            objectNode.put("balance", userBankInfoResponseDto.getBalance());
            objectNode.put("number", userBankInfoResponseDto.getCardNumber());
            objectNode.put("cardType", userBankInfoResponseDto.getCardType().toString());
            objectNode.put("paySystem", userBankInfoResponseDto.getPaySystem().toString());
            arrayNode.add(objectNode);
        }
        String jsonResponse = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(arrayNode);
        exchange.sendResponseHeaders(200, jsonResponse.length());
        OutputStream outputStream = exchange.getResponseBody();
        outputStream.write(jsonResponse.getBytes());
    }
}