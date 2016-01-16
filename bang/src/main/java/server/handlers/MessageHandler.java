package server.handlers;

import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;

public class MessageHandler {
    private final static MessageHandler INSTANCE = new MessageHandler();

    public static MessageHandler getInstance() {
        return INSTANCE;
    }

    public void handle(JSONObject object) throws JSONException, IOException {
        String type = object.getString("type").toLowerCase();
        switch (type) {
        case "game":
            GameHandler.handle(object);
            return;
        }
    }
}
