package web;

import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Offline SocketServer
 *
 * @author Bin Cheng
 */
@ServerEndpoint("/offline/{userID}")
public class WebSocketOffline {

    private static Map<String, Session> clients = new ConcurrentHashMap<>();

    @OnOpen
    public void onOpen(@PathParam("userID") String userID, Session session) {
        clients.put(userID, session);
    }

    static void sendMessage(String id, String message) {
        try {
            clients.get(id).getBasicRemote().sendText(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
