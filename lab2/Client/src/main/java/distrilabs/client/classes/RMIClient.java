/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package distrilabs.client.classes;

import distrilabs.interfaces.ISongProvider;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 *
 * @author 000430063
 */
public class RMIClient extends Thread {
    
    private final String host;
    private final int port;
    private ISongProvider songProvider;
    public Runnable OnStart;
    
    public RMIClient(){
        this.host = "localhost";
        this.port = 3000;
    }
    public RMIClient(String host, int port){
        this.host = host;
        this.port = port;
    }
    
    @Override
    public void run() {
        try {
            Registry registry = LocateRegistry.getRegistry(this.host, this.port);
            this.songProvider = (ISongProvider) registry.lookup("SongProvider");
            if(this.OnStart != null){
                this.OnStart.run();
            }
            registry.unbind(host);
        } catch (NotBoundException | RemoteException e) {
            System.out.println("[client] error" + e);
        }
    }
    
    public ISongProvider getSongProvider() {
        return this.songProvider;
    }
    
    public void close() {
        this.interrupt();
    }

}