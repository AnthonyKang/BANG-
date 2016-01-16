package server;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.json.JSONObject;

import server.handlers.MessageHandler;

public class Server {
    private static final Server INSTANCE = new Server();
    private final MessageHandler handler = MessageHandler.getInstance();

    private Set<Socket> sockets = new HashSet<>();
    private Map<String, Game> games = new HashMap<>();

    /**
     * Private constructor to prevent additional servers being created
     */
    private Server() {
        // nothing
    }

    public static Server getInstance() {
        return INSTANCE;
    }

    /**
     * Handles user joining server. Sends back user's default name and sends
     * updated user list to all.
     * 
     * @param socket
     *            user socket that is conencting
     * @throws IOException
     */
    public void join(Socket socket) throws IOException {
        sockets.add(socket);
        // send reply message
        JSONObject object = new JSONObject();
        object.put("msgType", "connected");
        object.put("username", socket.session.getId());
        socket.session.getBasicRemote().sendText(object.toString());
        sendUserList();
        sendGameList();
    }

    /**
     * Handles user leaving server and sends updated user list to all
     * 
     * @param socket
     *            user socket that is disconnecting
     * @throws IOException
     */
    public void leave(Socket socket) throws IOException {
        sockets.remove(socket);
        sendUserList();
    }

    /**
     * Send message to all connected users
     * 
     * @param message
     *            message to send
     * @throws IOException
     */
    public void sendAll(String message) throws IOException {
        for (Socket s : sockets) {
            s.session.getBasicRemote().sendText(message);
        }
    }

    /**
     * Send user list to all connected users
     * 
     * @throws IOException
     */
    public void sendUserList() throws IOException {
        JSONObject object = new JSONObject();
        object.put("msgType", "userlist");
        object.put("userlist", getUserList());
        System.out.println("sending user list");
        sendAll(object.toString());
    }

    /**
     * Get list of all connected users
     * 
     * @return
     *         list of users
     */
    public List<String> getUserList() {
        return sockets.stream().map(x -> x.session.getId()).collect(Collectors.toList());
    }

    /**
     * Create a game with specified name
     * 
     * @param gameName
     *            game name
     * @throws IOException
     */
    public void createGame(String gameName) throws IOException {
        if (gameName.equals("") || games.containsKey(gameName)) {
            return;
        }
        games.put(gameName, new Game(gameName));
        sendGameList();
    }

    public void sendGameList() throws IOException {
        JSONObject object = new JSONObject();
        object.put("msgType", "gamelist");
        object.put("gamelist", getGameList());
        System.out.println(object.toString());
        sendAll(object.toString());
    }

    /**
     * Get list of all games
     * 
     * @return
     *         list of game names
     */
    public Map<String, String[]> getGameList() {
        return games.entrySet().stream().collect(Collectors.toMap(x -> x.getKey(), x -> x.getValue().getPlayerNames()));
    }
}
