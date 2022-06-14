package vttp2022.day07.books;

import java.io.Console;
import java.util.List;

public class Session implements Runnable {
    public static final String SEARCH = "search ";
    public static final String COUNT = "count ";
    public static final String EXIT = "exit";
    
    private final BooksDatabase booksDB;

    public Session(BooksDatabase db) {
        booksDB = db;
    }

    @Override
    public void run() {
        Console cons = System.console();
        boolean stop = false;
        List<String> result;
        while (!stop) {
            String input = cons.readLine("> ");
            input = input.trim();

            if (input.startsWith(SEARCH)) {
                input = input.substring(SEARCH.length()).trim();
                result = booksDB.search(input);
                if (result.size() > 0)
                    printList(result);
                else 
                    System.out.printf("Your search of '%s' returns no result\n", input);

            } else if (input.startsWith(COUNT)) {
                input = input.substring(COUNT.length()).trim();
                result = booksDB.search(input);
                if (result.size() > 0)
                    System.out.printf("There are %d books with the phrase '%s' in the title\n", result.size(), input);
                else
                    System.out.printf("There are no books with the phrase '%s' in its title\n", input);

            } else if (EXIT.equals(input)) 
                stop = true;

            else 
                System.err.printf("Unknow command: '%s'\n", input);

            System.out.println();
        }
    }

    private void printList(List<String> books) {
        for (int i = 0; i < books.size(); i++)
            System.out.printf("%d. %s\n", i + 1, books.get(i));
    }
}
