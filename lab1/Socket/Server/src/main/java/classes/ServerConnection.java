/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classes;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author admin
 */
public class ServerConnection extends Thread implements IServerConnection {
    
    private final Socket socket;
    private final HashMap<String, IEventHandler> events;
    private final ObjectOutputStream outStream;
    private final ObjectInputStream inStream;
    
    public ServerConnection(
            Socket socket,
            HashMap<String, IEventHandler> events
    ) throws IOException {
        this.socket = socket;
        this.events = events;
        this.outStream = new ObjectOutputStream(this.socket.getOutputStream());
        this.inStream = new ObjectInputStream(this.socket.getInputStream());
    }
    
    @Override
    public void run() {
        try {
            
            boolean flag = false;
            
            do {
                try {
                    Object obj = this.inStream.readObject();
                    if(obj instanceof Event<?>) {
                        Event<Object> event = (Event<Object>) obj;
                        flag = this.HandleMessage(event);
                    }
                } catch (ClassNotFoundException ex) {
                    System.out.println("[server] could not deserialize the event: ");
                }
            } while (!flag);
            
        } catch (IOException ex) {
            System.out.println("[server] Server exception: " + ex.getMessage());
        }
    }
    
    private boolean HandleMessage(Event<Object> event){
        String eventName = event.getName();
        
        IEventHandler eventHandler = this.events.get(eventName);
        
        if(eventHandler != null){
            eventHandler.handleEvent(event, this);
        } else if (eventName.equals("CLOSE")) {
            this.Close();
            return true;
        }
        
        return false;
    }
    
    @Override
    public void Send(String eventName, Object data) throws IOException {
        this.outStream.writeObject(new Event<>(eventName, data));
    }
    
     @Override
    public void Close() {
        try {
            this.outStream.close();
        } catch (IOException ex) {
            Logger.getLogger(ServerConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            this.inStream.close();
        } catch (IOException ex) {
            Logger.getLogger(ServerConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        this.interrupt();
        
        System.out.println("[server] The connection was closed.");
    }
}
