package server;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import lombok.Data;
import server.observer.Observer;
import server.utility.JSONBuilder;

@Data
public class Server {
    private static final Server instance = new Server();

    private final Observer observer = Observer.getInstance();

    private Map<String, Socket> users = new HashMap<>();

    /**
     * Private constructor to prevent additional servers being created
     */
    private Server() {
        // nothing
    }

    public static Server getInstance() {
        return instance;
    }

    /**
     * Handles user joining server. Sends back user's default name and sends
     * updated user list to all.
     * 
     * @param socket
     *            user socket that is connecting
     * @throws IOException
     */
    public void join(Socket socket) throws IOException {
        users.put(socket.session.getId(), socket);
        // send reply message
        socket.session.getBasicRemote().sendText(JSONBuilder.build("connected", socket.session.getId()));
        observer.update();
    }

    /**
     * Handles user leaving server and sends updated user list to all
     * 
     * @param socket
     *            user socket that is disconnecting
     * @throws IOException
     */
    public void leave(Socket socket) throws IOException {
        users.remove(socket.session.getId());
        observer.update();
    }

    /**
     * Send message to all connected users
     * 
     * @param message
     *            message to send
     * @throws IOException
     */
    public void sendAll(String message) throws IOException {
        System.out.println("sending all: " + message);
        for (Socket s : users.values()) {
            s.session.getBasicRemote().sendText(message);
        }
    }

    /**
     * Send message to specified user
     * 
     * @param socket
     * @param message
     * @throws IOException
     */
    public void send(Socket socket, String message) throws IOException {
        socket.session.getBasicRemote().sendText(message);
    }
}
