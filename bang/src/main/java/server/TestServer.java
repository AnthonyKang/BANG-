package server;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class TestServer {
    private static final TestServer INSTANCE = new TestServer();
    private static Set<TestSocket> sockets = new HashSet<>();

    public static TestServer getInstance() {
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
