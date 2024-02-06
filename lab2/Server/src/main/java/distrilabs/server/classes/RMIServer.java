/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package distrilabs.server.classes;

import distrilabs.interfaces.ISongProvider;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author 000430063
 */
public class RMIServer extends Thread {
    
    private final String host;
    private final int port;
    
    public RMIServer(){
        this.host = "localhost";
        this.port = 3000;
    }
    public RMIServer(String host, int port){
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
            System.out.println("[server] rmi server started");
        } catch (RemoteException ex) {
            System.out.println("[server] cannot connect to the server");
        }
    }
    
    public void close() {
        this.interrupt();
    }
    
}