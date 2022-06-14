package vttp2022.day07.books;

import java.io.IOException;

public class App {
    public static void main( String[] args ) throws IOException {
        BooksDatabase db = new BooksDatabase(args[0]);
        Session sess = new Session(db);
        sess.run();
    }
}
