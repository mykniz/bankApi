package converter;

import java.util.HashMap;
import java.util.Map;

public class QueryToMapParser {

    /**
     * parse URI parameters to map
     * @param query String from URI query
     * @return map with params
     */
    public static Map<String, String> queryToMap(String query) {
        Map<String, String> result = new HashMap();
        for (String param : query.split("&")) {
            String[] pair = param.split("=");
            if (pair.length > 1) {
                result.put(pair[0], pair[1]);
            } else {
                result.put(pair[0], "");
            }
        }
        return result;
    }
}