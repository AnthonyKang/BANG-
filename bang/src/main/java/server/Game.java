package server;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.json.JSONObject;

import lombok.Data;
import server.observer.Observer;
import server.utility.JSONBuilder;

@Data
public class Game {
    private static int MAX_PLAYERS = 7;

    private final Observer observer = Observer.getInstance();

    private String creator;
    private String name;
    private boolean started = false;
    // map between player id and their socket
    private Map<String, Socket> players = new HashMap<>();

    public Game(String creator, String name) {
        this.creator = creator;
        this.name = name;
    }

    /**
     * Add player to game
     * 
     * @param socket
     * @return
     * @throws IOException
     */
    public boolean joinGame(Socket socket) throws IOException {
        // json object to send success message to client
        JSONObject object = new JSONObject();
        object.put("gameName", this.name);

        // check if game has started or reached max players
        if (started || players.size() >= MAX_PLAYERS) {
            object.put("status", false);
            Server.getInstance().send(socket, JSONBuilder.build("joingame", object));
            return false;
        }

        players.put(socket.session.getId(), socket);
        observer.update();

        object.put("status", true);
        Server.getInstance().send(socket, JSONBuilder.build("joingame", object));

        return true;
    }

    /**
     * Remove player from game.
     * 
     * @param userName
     * @throws IOException
     */
    public void leaveGame(String userName) throws IOException {
        // TODO: error checking? might only be on client side to fuck this up
        // json object to send success message to client
        JSONObject object = new JSONObject();
        object.put("gameName", this.name);
        object.put("status", true);
        Server.getInstance().send(players.get(userName), JSONBuilder.build("leavegame", object));

        players.remove(userName);
        observer.update();
    }

    /**
     * Start game
     */
    public void startGame() {
        this.started = true;
    }

    /**
     * Returns list of player names
     * TODO: use array list instead?
     * 
     * @return
     *         array of player names
     */
    public List<String> getPlayerNames() {
        return players.keySet().stream().collect(Collectors.toList());
    }
}
