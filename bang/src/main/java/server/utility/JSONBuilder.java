package server.utility;

import org.json.JSONObject;

public class JSONBuilder {
    public static String build(String type, Object data) {
        JSONObject object = new JSONObject();
        object.put("type", type);
        object.put("data", data);
        return object.toString();
    }
}
