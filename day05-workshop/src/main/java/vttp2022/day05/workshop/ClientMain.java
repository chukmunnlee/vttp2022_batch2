package vttp2022.day05.workshop;

import java.io.Console;
import java.io.IOException;
import java.net.Socket;

public class ClientMain {

    public static void main(String[] args) throws IOException {

        String host = "localhost";
        int port = 3000;
        if (args.length >= 2) {
            host = args[0];
            port = Integer.parseInt(args[1]);
        } else if (args.length == 1) 
            port = Integer.parseInt(args[0]);

        //Connect to the server
        System.out.printf("Connect to server %s on port %d\n", host, port);
        Socket sock = new Socket(host, port);
        System.out.println("Connected...");

        NetworkIO netIO = new NetworkIO(sock);
        Console cons = System.console();
        String req = "";
        String resp = "";

        while (!req.equals("exit")) {
            req = cons.readLine("> ");
            if (req.trim().equals("exit"))
                break; // break out from the closest loop
            netIO.write(req);
            resp = netIO.read();
            System.out.printf(">> %s\n", resp);
        }

        netIO.close();
        sock.close();

        System.out.println("Terminating client...");
    }
}
