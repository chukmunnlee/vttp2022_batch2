package vttp2022.day07;

import java.security.SecureRandom;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class App {
    public static void main( String[] args ) {
        // Create a thread pool
        ExecutorService thrPool = Executors.newFixedThreadPool(4);
        Random rand = new SecureRandom();
        for (int i = 0; i < 10; i++) {
            String msg = "Thread-%d".formatted(i);
            int sec = rand.nextInt(1, 5);
            System.out.printf("\tDispatching %s to pool\n", msg);

            ThrMain thr = new ThrMain(msg, sec);
            thrPool.submit(thr);
        }
        System.out.println( "Main thread: Dispatched all thread" );
    }
}
