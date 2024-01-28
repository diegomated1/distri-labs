/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package classes;

import java.io.IOException;

/**
 *
 * @author Diego
 */
public interface IServerConnection {
    public void Send(String eventName, Object data) throws IOException;
    public void Close();
}