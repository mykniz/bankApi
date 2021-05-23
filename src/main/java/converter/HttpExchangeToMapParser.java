package converter;

import com.sun.net.httpserver.HttpExchange;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class HttpExchangeToMapParser {

    /**
     * parse JSON from POST method to map
     */
    public static Map<String, String> httpExchangeToMap(HttpExchange exchange) {
        Map<String, String> map = new HashMap<>();
        try (InputStreamReader inputStreamReader = new InputStreamReader(exchange.getRequestBody())) {
            BufferedReader br = new BufferedReader(inputStreamReader);
            String body = br.readLine();
             map = HttpExchangeToMapParser.requestBodyToMap(body);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }


    private static Map<String, String> requestBodyToMap(String requestBody) {
        String croppedRequestBody = requestBody.replaceAll("[\\s{}\"]", "");
        Map<String, String> result = new HashMap();
        for (String param : croppedRequestBody.split(",")) {
            String pair[] = param.split(":");
            if (pair.length > 1) {
                result.put(pair[0], pair[1]);
            } else {
                result.put(pair[0], "");
            }
        }
        return result;
    }
}
