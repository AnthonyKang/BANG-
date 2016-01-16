package server.handlers;

import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;

import server.Server;

public class GameHandler {
    private static final int MAX_GAMES = 5;
    private static int numGames = 0;
    private static Server server = Server.getInstance();

    public static void handle(JSONObject object) throws JSONException, IOException {
        JSONObject data = object.getJSONObject("data");
        String type = data.getString("type");
        switch (type) {
        case "create":
            server.createGame(data.getString("data"));
            break;
        case "join":
        case "leave":
        case "delete":
            break;
        default:
            throw new IllegalArgumentException("Invalid game action");
        }
    }
}
