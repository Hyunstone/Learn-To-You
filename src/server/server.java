package server;

import database.Total;
import network.MultiChatServer;

public class server {
    // 여기도 없앨수도 O
    Total total;
    ServerController serverController;

    public server(Total total) {
        this.total = total;
        serverController = new ServerController(total);
        MultiChatServer chatServer = new MultiChatServer();
        chatServer.start();
    }



}
