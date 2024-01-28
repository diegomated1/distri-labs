/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classes;

import java.io.IOException;
import java.util.HashMap;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Diego
 */
public class ClientConnection extends Thread implements IClientConnection {
    
    private final Socket socket;
    private HashMap<String, IEventHandler> events;
    private final ObjectOutputStream outStream;
    private final ObjectInputStream inStream;
    
    public ClientConnection(
        Socket socket,
        HashMap<String, IEventHandler> events
    ) throws IOException {
        this.socket = socket;
        this.events = events;
        this.outStream = new ObjectOutputStream(this.socket.getOutputStream());
        this.inStream = new ObjectInputStream(this.socket.getInputStream());
        
        this.Verifi();
    }
    
    private void Verifi() {
        IEventHandler eventHandlerOnStart = this.events.get("START");
        if(eventHandlerOnStart != null){
            eventHandlerOnStart.handleEvent(null, this);
        }
    }
    
    @Override
    public void run(){
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
                    System.out.println("[client] unexpected error.");
                }
            } while (!flag);
            
            System.out.println("[server] adios cliente");
            
        } catch (IOException ex) {
            System.out.println("[client] Server exception: " + ex.getMessage());
        }
    }
    
    private boolean HandleMessage(Event<Object> event){
        String eventName = event.getName();
        
        IEventHandler eventHandler = this.events.get(eventName);
        
        if(eventHandler != null){
            eventHandler.handleEvent(event, this);
        } else if (eventName.equals("CLOSE")) {
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
            Logger.getLogger(ClientConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            this.inStream.close();
        } catch (IOException ex) {
            Logger.getLogger(ClientConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        this.interrupt();
        
        System.out.println("[Client] The connection was closed.");
    }
    
}

