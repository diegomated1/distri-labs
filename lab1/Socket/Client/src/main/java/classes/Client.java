/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classes;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.HashMap;

/**
 *
 * @author admin
 */
public class Client extends Thread {

    private final Socket socket;
    private final HashMap<String, IEventHandler> events;
    private final String host;
    private final int port;
    
    public Client(String host, int port) {
        this.socket = new Socket();
        this.events = new HashMap<>();
        this.host = host;
        this.port = port;
    }
    
    public void On(String eventName, IEventHandler event){
        this.events.put(eventName, event);
    }
    
    private void handleConnection() {
        try {
            new ClientConnection(this.socket, this.events).start();
        } catch (IOException ex) {
            System.out.println("[client] Unexpected error.");
        }
    }
    
    @Override
    public void run() {
        try {
            socket.connect(new InetSocketAddress(host, port), 1000);
        } catch (IOException ex) {
            System.out.println("[client] failed to connect to the server.");
        }
        this.handleConnection();
    }
    
}
