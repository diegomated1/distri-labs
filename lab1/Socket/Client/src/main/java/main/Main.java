/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package main;

import classes.Client;
import main.Events.OnNewSong;
import main.Events.OnStart;

/**
 *
 * @author Diego
 */
public class Main {

    public static void main(String[] args) {
        
        Client client = new Client("127.0.0.1", 3000);
        
        client.On("START", new OnStart());
        client.On("NEW:SONG", new OnNewSong());
        
        client.start();
    }
}
