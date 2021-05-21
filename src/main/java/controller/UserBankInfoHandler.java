package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import config.ServerConfig;
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
    public void handle(HttpExchange exchange) {

        ObjectMapper objectMapper = new ObjectMapper();
        List<UserBankInfoResponseDto> usersBankInfo = userService.findUsersBankInfo();

        try {
            String jsonResponse = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(usersBankInfo);
            exchange.sendResponseHeaders(ServerConfig.STATUS_OK, jsonResponse.length());
            OutputStream outputStream = exchange.getResponseBody();
            outputStream.write(jsonResponse.getBytes());
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}