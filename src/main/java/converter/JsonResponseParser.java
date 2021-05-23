package converter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import config.ServerConfig;

import java.io.IOException;
import java.io.OutputStream;

public class JsonResponseParser {

    public static void toJsonResponse(HttpExchange exchange, Object o) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonResponse = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(o);
            exchange.sendResponseHeaders(ServerConfig.STATUS_OK, jsonResponse.length());
            OutputStream outputStream = exchange.getResponseBody();
            outputStream.write(jsonResponse.getBytes());
            exchange.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
