/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Server;

import Interfaces.ISongProvider;
import Server.Classes.SongProvider;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author 000430063
 */
public class Server extends Thread {
    
    private final String host;
    private final int port;
    
    public Server(){
        this.host = "localhost";
        this.port = 3000;
    }
    public Server(String host, int port){
        this.host = host;
        this.port = port;
    }
    
    @Override
    public void run() {
        System.setProperty("java.rmi.server.hostname", this.host);
        
        ISongProvider songProvider = new SongProvider();
        
        try {
            ISongProvider stub = (ISongProvider) UnicastRemoteObject.exportObject(songProvider, this.port);
            Registry registry = LocateRegistry.createRegistry(this.port);
            registry.rebind("SongProvider", stub);
        } catch (RemoteException ex) {
            System.out.println("[server] cannot connect to the server");
        }
    }
    
    public void close() {
        this.interrupt();
    }
    
}
