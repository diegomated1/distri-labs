/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package distrilabs.server;

import distrilabs.server.classes.RMIServer;

/**
 *
 * @author admin
 */
public class Server {

    public static void main(String[] args) {
        
        RMIServer sv = new RMIServer();
        sv.start();
        
    }
}
