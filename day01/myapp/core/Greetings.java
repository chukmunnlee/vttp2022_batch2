package myapp.core;

import java.io.*;

public class Greetings {

    // write entry point
    public static void main(String[] args) {
        // Get the console
        Console cons = System.console();

        // Read from input
        String name = cons.readLine("What you your name? ");

        System.out.printf("[%d] Hello %s. Nice to meet you.\n", 10, name.toUpperCase());
    }
    
}
