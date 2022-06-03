package vttp2022.day05.workshop;

import java.io.IOException;
import java.net.Socket;

public class ClientThread implements Runnable {

    private Socket sock;

    public ClientThread(Socket s) {
        sock = s;
    }

    @Override
    public void run() {
        // run() is the thread's body
        System.out.println("Starting client thread");
        try {
            NetworkIO netIO = new NetworkIO(sock);
            String req = "";

            while (!req.equals("exit")) {
                try {
                    req = netIO.read();
                    System.out.printf("[client] %s\n", req);
                    if (req.trim().equals("exit"))
                        break; // break out from the closest loop
                    netIO.write(req.toUpperCase());
                } catch (IOException ex) {
                    // exit when there is a network connection
                    break; // break out from the closest loop
                }
            }

            netIO.close();
            sock.close();
            System.out.println("Exiting thread");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
}
