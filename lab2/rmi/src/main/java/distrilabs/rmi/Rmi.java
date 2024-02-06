 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package distrilabs.rmi;

import Client.Client;
import Interfaces.ISong;
import Interfaces.ISongProvider;
import Server.Server;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author 000430063
 */
public class Rmi {

    public static void main(String[] args) {
        
        Server sv = new Server();
        Client cl = new Client();
        
        sv.start();
        cl.start();
        
        cl.OnStart = () -> {
            ISongProvider songProvider = cl.getSongProvider();
            
            System.out.println("[client] Connection Successful.");
        
            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.println("[client] Enter the filter type: ");
                System.out.println("1) Title");
                System.out.println("2) Genre");
                System.out.println("3) Author");

                String filterScan = scanner.nextLine();

                System.out.print("Enter the search term: ");
                String q = scanner.nextLine();
                List<ISong> songs = new ArrayList<>();

                try {
                    switch (filterScan) {
                        case "1" -> songs = songProvider.getSongsByTitle(q);
                        case "2" -> songs = songProvider.getSongsByGenre(q);
                        case "3" -> songs = songProvider.getSongsByAuthor(q);
                        default -> {
                            System.out.println("[client] Invalid filter type");
                            continue;
                        }
                    }
                } catch (RemoteException ex) {
                    System.out.println("[client] Failed to get new songs");
                }

                songs.forEach((song) -> {
                    System.out.println("[client] NEW SONG: " + song.toString());
                });

                while(true){
                    System.out.println("[client] Do you want to get more songs? (y / n)");
                    String answer = scanner.nextLine();
                    if(answer.equals("y")) {
                        break;
                    } else if (answer.equals("n")) {
                        cl.close();
                        sv.close();
                        return;
                    }
                }
            }
        };
        
    }
}
