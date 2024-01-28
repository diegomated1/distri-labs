/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.Events;

import classes.Event;
import classes.IEventHandler;
import classes.IClientConnection;
import java.io.IOException;
import java.util.Scanner;
import main.Models.Filter;

/**
 *
 * @author Diego
 */
public class OnStart extends Thread implements IEventHandler {

    private Event<Object> data;
    private IClientConnection cl;
    
    private void SendFilter(Filter filter){
        int tries = 0;
        while(true){
            try {
                System.out.println("[client] Sending filter.");
                this.cl.Send("GET:SONGS", filter);
                return;
            } catch (IOException ex) {
                tries++;
            }
            if(tries == 3){
                System.out.println("[client] cannot send filter.");
            }
        }
    }
    
    @Override
    public void handleEvent(Event<Object> data, IClientConnection cl) {
        this.data = data;
        this.cl = cl;
        this.start();
    }
    
    @Override
    public void run(){
        System.out.println("[client] Connection Succesfull.");
        
        Scanner scanner = new Scanner(System.in);
        
        while (true) {
            System.out.println("\n### SONGS ###");
            System.out.println("1) Get songs");
            
            String action = scanner.nextLine();
            
            if(action.equals("1")){
                System.out.println("Enter the filter type: ");
                System.out.println("1) Title");
                System.out.println("2) Genre");
                System.out.println("3) Author");
                System.out.println("4) Language");
                System.out.println("5) LaunchYear");
                
                String filterScan = scanner.nextLine();
                String filterType;
                
                switch (filterScan) {
                    case "1" -> filterType = "title";
                    case "2" -> filterType = "genre";
                    case "3" -> filterType = "author";
                    case "4" -> filterType = "language";
                    case "5" -> filterType = "launchYear";
                    default -> {
                        System.out.println("Invalid filter");
                        continue;
                    }
                }
                
                System.out.print("Enter the search term: ");
                String q = scanner.nextLine();
                
                Filter filter = new Filter(filterType, q);
                SendFilter(filter);
                
                while(true){
                    System.out.println("Do you want to get more songs? (y / n)");
                    String answer = scanner.nextLine();
                    if(answer.equals("y")) {
                        break;
                    } else if (answer.equals("n")) {
                        cl.Close();
                        return;
                    }
                }
            } else {
                System.out.println("Invalid Action");
            }
        }
    }
    
}
