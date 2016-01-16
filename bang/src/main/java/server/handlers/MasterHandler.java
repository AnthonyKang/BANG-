package server.handlers;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

public class MasterHandler {
    private static final MasterHandler instance = new MasterHandler();

    private final Map<String, MessageHandler> handlers = new HashMap<>();

    public MasterHandler() {
        handlers.put("user", new UserHandler());
        handlers.put("game", new GameHandler());
    }

    public static MasterHandler getInstance() {
        return instance;
    }

    public MessageHandler getHandler(String handlerName) {
        return handlers.get(handlerName);
    }

    public void handle(JSONObject object) throws Exception {
        String type = object.getString("type");
        handlers.get(type).handle(object);
    }

}
