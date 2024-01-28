/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.Events;

import classes.Event;
import classes.IEventHandler;
import classes.IServerConnection;

/**
 *
 * @author Diego
 */
public class OnStart implements IEventHandler {

    @Override
    public void handleEvent(Event<Object> data, IServerConnection sv) {
        System.out.println("[server] server on 127.0.0.1:3000");
    }
    
}
