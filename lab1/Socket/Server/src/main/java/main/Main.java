/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package main;

import java.util.ArrayList;
import java.util.List;
import main.Events.OnGetSong;
import main.Events.OnStart;
import main.Models.Song;
import classes.Server;

/**
 *
 * @author Diego
 */
public class Main {

    public static void main(String[] args) {
        
        List<Song> songs = createSongs();
        
        Server sv = new Server("127.0.0.1", 3000);
        
        sv.On("START", new OnStart());
        sv.On("GET:SONGS", new OnGetSong(songs));
        
        sv.start();
    }
    
    private static List<Song> createSongs(){
        List<Song> songs = new ArrayList<>();

        songs.add(new Song("Song Title 1", "Genre 1", "Author 1", "Language 1", "2020"));
        songs.add(new Song("Song Title 2", "Genre 2", "Author 2", "Language 1", "2021"));
        songs.add(new Song("Song Title 3", "Genre 3", "Author 1", "Language 2", "2022"));
        songs.add(new Song("Song Title 4", "Genre 2", "Author 1", "Language 2", "2023"));

        return songs;
    }
    
}
