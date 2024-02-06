/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 *
 * @author 000430063
 */
public interface ISongProvider extends Remote {
    
    /**
     * @param title
     * @return
     * @throws java.rmi.RemoteException
     */
    public List<ISong> getSongsByTitle(String title) throws RemoteException;
    
    /**
     * @param title
     * @return
     * @throws java.rmi.RemoteException
     */
    public List<ISong> getSongsByGenre(String genre) throws RemoteException;

    /**
     * @param title
     * @return
     * @throws java.rmi.RemoteException
     */
    public List<ISong> getSongsByAuthor(String author) throws RemoteException;
    
}
