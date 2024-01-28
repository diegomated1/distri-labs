/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.Events;

import java.util.List;
import main.Models.Filter;
import main.Models.Song;
import classes.Event;
import classes.IEventHandler;
import classes.IServerConnection;
import java.io.IOException;

/**
 *
 * @author Diego
 */
public class OnGetSong implements IEventHandler {

    private final List<Song> songs;
    
    public OnGetSong(List<Song> songs){
        this.songs = songs;
    }
    
    private void SendSong(Song song, IServerConnection sv){
        int tries = 0;
        while(true){
            try {
                System.out.println("[client] Sending song.");
                sv.Send("NEW:SONG", song);
                return;
            } catch (IOException ex) {
                tries++;
            }
            if(tries == 3){
                System.out.println("[server] cannot send song.");
            }
        }
    }
    
    @Override
    public void handleEvent(Event<Object> data, IServerConnection sv) {
        Filter filter = (Filter) data.getData();
        
        System.out.println(filter.toString());

        String _filter = filter.getFilter();
        String _q = filter.getQ();

        for (Song song : this.songs) {
            boolean shouldSendSong = false;
            switch (_filter) {
                case "title" -> shouldSendSong = song.getTitle().equals(_q);
                case "genre" -> shouldSendSong = song.getGenre().equals(_q);
                case "author" -> shouldSendSong = song.getAuthor().equals(_q);
                case "language" -> shouldSendSong = song.getLanguage().equals(_q);
                case "launchYear" -> shouldSendSong = song.getLaunchYear().equals(_q);
                default -> { }
            }
            if (shouldSendSong) {
                SendSong(song, sv);
            }
        }
    }
    
}
