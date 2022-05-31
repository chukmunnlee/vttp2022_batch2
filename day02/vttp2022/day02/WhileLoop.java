package vttp2022.day02;

import java.io.Console;

public class WhileLoop {

    public static void main(String[] args) {

        Console cons = System.console();

        String input = "ABC";

        while (input.length() > 0) {
            input = cons.readLine("Please enter a text. Blank to end ");
            if (input.length() > 0) {
                System.out.printf(">>> %s\n", input.toUpperCase());
            }
        }
        System.out.println("Bye bye");
    }
    
}
