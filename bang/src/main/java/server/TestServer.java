package server;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.json.JSONObject;

public class TestServer {
    private static final TestServer INSTANCE = new TestServer();
    private static Set<TestSocket> sockets = new HashSet<>();

    public static TestServer getInstance() {
        return INSTANCE;
    }

    public void join(TestSocket socket) throws IOException {
        sockets.add(socket);
        // send reply message
        JSONObject object = new JSONObject();
        object.put("msgType", "connected");
        object.put("username", socket.session.getId());
        socket.session.getBasicRemote().sendText(object.toString());
        sendUserList();
    }

    public void part(TestSocket socket) throws IOException {
        sockets.remove(socket);
        sendUserList();
    }

    public void sendAll(String message) throws IOException {
        for (TestSocket s : sockets) {
            s.session.getBasicRemote().sendText(message);
        }
    }

    public void sendUserList() throws IOException {
        JSONObject object = new JSONObject();
        object.put("msgType", "userlist");
        object.put("userlist", getUserList());
        System.out.println("sending user list");
        sendAll(object.toString());
    }

    public List<String> getUserList() {
        return sockets.stream().map(x -> x.session.getId()).collect(Collectors.toList());
    }
}
