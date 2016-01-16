package server;

import java.util.HashMap;
import java.util.Map;

import lombok.Data;

@Data
public class Game {
    private String name;
    private String creator;
    private boolean started = false;
    private Map<String, Socket> players = new HashMap<>();

    public Game(String name) {
        this.name = name;
    }

    public boolean joinGame(Socket socket) {
        if (started) {
            return false;
        }
        players.put(socket.session.getId(), socket);
        return true;
    }

    public void leaveGame(Socket socket) {
        players.remove(socket.session.getId());
    }

    public void startGame() {
        this.started = true;
    }

    public String[] getPlayerNames() {
        String[] playerNames = new String[players.size()];
        return players.keySet().toArray(playerNames);
    }
}
