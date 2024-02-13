/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package distrilabs.filestopdf;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @author Dego
 */
public class FilesToPdf {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        while(true){
            List<String> filePaths = new ArrayList<>();
            System.out.println("\nAdd links:");
            System.out.println(" > <file path>");
            System.out.println(" > generate");
            System.out.println(" > exit");
            
            while(true){
                String command = scanner.nextLine();
                
                if(command.equals("generate")){
                    System.out.print("number of threads: ");
                    int threads;
                    try{
                        threads = Integer.parseInt(scanner.nextLine());
                    }catch (NumberFormatException ex) {
                        System.out.println("Invalid integer");
                        break;
                    }
                    System.out.print("pdfs output path: ");
                    String outputPath = scanner.nextLine();
                    generatePdf(filePaths, threads, outputPath);
                    break;
                } else if (command.equals("exit")) {
                    return;
                } else {
                    filePaths.add(command);
                }
            }
        }
    }

    private static void generatePdf(List<String> filePaths, int threads, String outputPath){
        long startTime = System.currentTimeMillis();
        
        AtomicInteger treads_ended = new AtomicInteger(0);
        
        int links_size = filePaths.size();
        
        int group_size = links_size / threads;
        for(int i=0;i<threads;i++){
            
            final int c_i = i * group_size;
            
            new Thread(() -> {
                
                long startTimeLocal = System.currentTimeMillis();
                for(int j=c_i;j<group_size+c_i;j++){
                    System.out.println(String.format("THREAD %s | PROCESS %s: STARTED", c_i, j));
                    LibreOffice.convertToPdf(filePaths.get(j), outputPath);
                    System.out.println(String.format("THREAD %s | PROCESS %s: DONE", c_i, j));
                }
                long endTimeLocal = System.currentTimeMillis();
                
                System.out.println(String.format("THREAD %s DONE IN: %d ms", c_i, (endTimeLocal - startTimeLocal)));
                treads_ended.addAndGet(1);
            }).start();
        }
        
        while(true){
            if(treads_ended.get() == threads){
                long endTime = System.currentTimeMillis();
                System.out.println(String.format("DONE IN: %d ms",(endTime - startTime)));
                break;
            }
        }
    }
}
