package server;

import java.io.IOException;

import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.json.JSONObject;

import server.handlers.MessageHandler;

@ServerEndpoint("/testsocket")
public class Socket {
    public Session session;
    private final MessageHandler handler = MessageHandler.getInstance();

    @OnOpen
    public void onOpen(Session session) throws IOException {
        this.session = session;
        System.out.println("WebSocket opened: " + session.getId());
        Server.getInstance().join(this);
    }

    @OnMessage
    public void onMessage(String message, Session session) throws IOException {
        System.out.println("Message received: " + message);
        // TestServer.getInstance().sendAll(message);
        handler.handle(new JSONObject(message));
    }

    @OnClose
    public void onClose(CloseReason reason, Session session) throws IOException {
        System.out.println("Closing a WebSocket due to " + reason.getReasonPhrase());
        Server.getInstance().leave(this);
    }
}
