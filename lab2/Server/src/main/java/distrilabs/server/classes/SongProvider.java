/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package distrilabs.server.classes;

import classes.Song;
import distrilabs.interfaces.ISong;
import distrilabs.interfaces.ISongProvider;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author 000430063
 */
public class SongProvider implements ISongProvider {

    private final List<ISong> songs;
    
    public SongProvider(){
        this.songs = this.defaultSongs();
    }
    
    public SongProvider(List<ISong> songs){
        this.songs = songs;
    }
    
    @Override
    public List<ISong> getSongsByTitle(String title) throws RemoteException {
        List<ISong> songs = new ArrayList();
        for (ISong song : this.songs) {
            if(title.equals(song.getTitle())){
                songs.add(song);
            }
        }
        return songs;
    }

    @Override
    public List<ISong> getSongsByGenre(String genre) throws RemoteException {
        List<ISong> songs = new ArrayList();
        for (ISong song : this.songs) {
            if(genre.equals(song.getGenre())){
                songs.add(song);
            }
        }
        return songs;
    }

    @Override
    public List<ISong> getSongsByAuthor(String author) throws RemoteException {
        List<ISong> songs = new ArrayList();
        for (ISong song : this.songs) {
            if(author.equals(song.getAuthor())){
                songs.add(song);
            }
        }
        return songs;
    }
    
    
    
    private List<ISong> defaultSongs(){
        List<ISong> songs = new ArrayList<>();

        songs.add(new Song("Song Title 1", "Genre 1", "Author 1", "Language 1", "2020"));
        songs.add(new Song("Song Title 2", "Genre 2", "Author 2", "Language 1", "2021"));
        songs.add(new Song("Song Title 3", "Genre 3", "Author 1", "Language 2", "2022"));
        songs.add(new Song("Song Title 4", "Genre 2", "Author 1", "Language 2", "2023"));

        return songs;
    }
    
}
