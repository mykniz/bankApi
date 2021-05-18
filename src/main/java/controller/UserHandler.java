package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import entity.User;
import service.UserService;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.List;

public class UserHandler implements HttpHandler {

    UserService userService;

    public UserHandler(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        ArrayNode arrayNode = objectMapper.createArrayNode();

        try {
            List<User> userList = userService.findAll();
            for (User user : userList) {

                ObjectNode objectNode = objectMapper.createObjectNode();
                objectNode.put("firstname", user.getFirstName());
                objectNode.put("lastname", user.getLastName());
                arrayNode.add(objectNode);
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

        String jsonResponse = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(arrayNode);
        exchange.sendResponseHeaders(200, jsonResponse.length());
        OutputStream outputStream = exchange.getResponseBody();
        outputStream.write(jsonResponse.getBytes());
    }
}
