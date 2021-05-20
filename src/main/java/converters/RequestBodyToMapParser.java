package converters;

import java.util.HashMap;
import java.util.Map;

public class RequestBodyToMapParser {

    public static Map<String, String> requestBodyToMap(String requestBody) {
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
