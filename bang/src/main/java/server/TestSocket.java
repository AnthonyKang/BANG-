package server;

import java.io.IOException;

import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/testsocket")
public class TestSocket {
    public Session session;

    @OnOpen
    public void onOpen(Session session) throws IOException {
        this.session = session;
        System.out.println("WebSocket opened: " + session.getId());
        TestServer.getInstance().join(this);
    }

    @OnMessage
    public void onMessage(String message, Session session) throws IOException {
        System.out.println("Message received: " + message);
        TestServer.getInstance().sendAll(message);
    }

    @OnClose
    public void onClose(CloseReason reason, Session session) throws IOException {
        System.out.println("Closing a WebSocket due to " + reason.getReasonPhrase());
        TestServer.getInstance().part(this);
    }
}
