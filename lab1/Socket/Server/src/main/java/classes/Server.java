/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classes;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

/**
 *
 * @author admin
 */
public class Server extends Thread {
    
    private ServerSocket server;
    private final HashMap<String, IEventHandler> events;
    private final String host;
    private final int port;
    
    public Server(String host, int port){
        this.events = new HashMap<>();
        this.host = host;
        this.port = port;
    }
    
    public void On(String eventName, IEventHandler event){
        this.events.put(eventName, event);
    }
    
    private void handleConnections() {
        while (true) {
            Socket sc;
            try {
                sc = this.server.accept();
                new ServerConnection(sc, this.events).start();
            } catch (IOException ex) {
                System.out.println("[server] Could not connect to the client.");
            }
        }
    }
    
    @Override
    public void run() {
        try {
            this.server = new ServerSocket(this.port, 50, InetAddress.getByName(host));
        } catch (IOException ex) {
            System.out.println("[server] could not start the server.");
            this.interrupt();
            return;
        }
        IEventHandler eventHandlerOnStart = this.events.get("START");
        if(eventHandlerOnStart != null){
            eventHandlerOnStart.handleEvent(null, null);
        }
        this.handleConnections();
    }
    
}
