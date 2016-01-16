package server.handlers;

import org.json.JSONObject;

public interface MessageHandler {

    public void handle(JSONObject object) throws Exception;

}
