package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import config.ServerConfig;
import entity.User;
import service.UserService;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.List;

public class GetUsersHandler implements HttpHandler {

    private final UserService userService;

    public GetUsersHandler(UserService userService) {
        this.userService = userService;
    }

    /**
     * Receives GET request ant /users and return JSON array of clients
     * @param exchange
     */
    @Override
    public void handle(HttpExchange exchange) {

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            List<User> userList = userService.findAll();
            String jsonResponse = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(userList);
            exchange.sendResponseHeaders(ServerConfig.STATUS_OK, jsonResponse.length());
            OutputStream outputStream = exchange.getResponseBody();
            outputStream.write(jsonResponse.getBytes());
            exchange.close();
        } catch (SQLException | IOException exception) {
            exception.printStackTrace();
        }
    }
}
