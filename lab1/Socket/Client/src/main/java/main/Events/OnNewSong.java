/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.Events;

import classes.Event;
import classes.IClientConnection;
import classes.IEventHandler;
import main.Models.Song;

/**
 *
 * @author Diego
 */
public class OnNewSong implements IEventHandler {

    @Override
    public void handleEvent(Event<Object> data, IClientConnection sv) {
        Song song = (Song) data.getData();
        System.out.println(String.format("[client] NEW SONG: %s", song.toString()));
    }
    
}
