package vttp2022.day05.workshop;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerMain {
    public static void main(String[] args) throws IOException {

        int port = 3000;
        if (args.length > 0)
            port = Integer.parseInt(args[0]);

        // Create a thread pool
        ExecutorService threadPool = Executors.newFixedThreadPool(2);

        // Create the server
        System.out.printf("Starting the server on port %d\n", port);
        ServerSocket server = new ServerSocket(port);

        while (true) {
            // Wait for incoming connection
            System.out.println("Waiting for client connection");
            Socket sock = server.accept();
            System.out.println("Connected...");
            ClientThread thr = new ClientThread(sock);
            threadPool.submit(thr);
            System.out.println("Submitted to threadpool");
        }

        //System.out.println("Terminating server...");
    }
}
