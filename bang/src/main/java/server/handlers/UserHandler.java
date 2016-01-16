package server.handlers;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.json.JSONObject;

import server.Server;
import server.utility.JSONBuilder;

public class UserHandler implements MessageHandler {

    private static Server server = Server.getInstance();

    @Override
    public void handle(JSONObject object) throws Exception {
        JSONObject data = object.getJSONObject("data");
        String action = data.getString("action");
        switch (action) {
        case "getList":
            sendUserList();
            break;
        default:
            throw new IllegalArgumentException("Invalid game action");
        }
    }

    public void sendUserList() throws IOException {
        List<String> usernames = server.getUsers().keySet().stream().collect(Collectors.toList());
        server.sendAll(JSONBuilder.build("userlist", usernames));
    }

}
