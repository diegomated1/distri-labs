/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package distrilabs.urlstopdf;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

/**
 *
 * @author admin
 */
public class Chrome {
    
    private final ChromeDriver chromeDriver;
    
    public Chrome(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");     
        options.addArguments("--disable-gpu");
        options.addArguments("--window-size=800,800");  
        this.chromeDriver = new ChromeDriver(options);
    }
    
    public void getPdf(URL url, String outputPath) {
        chromeDriver.get(url.toString());
        
        Map<String, Object> params = new HashMap();
        Map<String, Object> output = chromeDriver.executeCdpCommand("Page.printToPDF", params);
        
        try (FileOutputStream fileOutputStream = new FileOutputStream(outputPath + "\\" + url.getHost() + ".pdf")) {
            byte[] byteArray = java.util.Base64.getDecoder().decode((String) output.get("data"));
            fileOutputStream.write(byteArray);
        }
        catch (IOException e)
        {
            System.out.println("exeption: " + e);
        }
    }
    
}
