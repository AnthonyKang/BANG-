package server;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class Server {
    private static final Server INSTANCE = new Server();
    private static Set<TestSocket> sockets = new HashSet<>();

    public static Server getInstance() {
        return INSTANCE;
    }

    public void join(TestSocket socket) {
        sockets.add(socket);
    }

    public void part(TestSocket socket) {
        sockets.remove(socket);
    }

    public void sendAll(String message) throws IOException {
        for (TestSocket s : sockets) {
            s.session.getBasicRemote().sendText(message);
        }
    }
}
