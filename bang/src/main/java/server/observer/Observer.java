package server.observer;

import java.io.IOException;

import server.handlers.GameHandler;
import server.handlers.MasterHandler;
import server.handlers.UserHandler;

public class Observer {
    private static final Observer instance = new Observer();

    private Observer() {
    }

    public static Observer getInstance() {
        return instance;
    }

    public void update() throws IOException {
        MasterHandler handler = MasterHandler.getInstance();
        ((UserHandler) handler.getHandler("user")).sendUserList();
        ((GameHandler) handler.getHandler("game")).sendGameList();
    }
}
