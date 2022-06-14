package vttp2022.day07.books;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;

public class BooksDatabase implements Predicate<String> {
    
    private final List<String> books = new LinkedList<>();
    private String searchTerms = "";

    public BooksDatabase(String book) throws IOException {

        FileReader fr = new FileReader(book);
        BufferedReader br = new BufferedReader(fr);
        String line;
        while (null != (line = br.readLine()))
            books.add(line);
        fr.close();
    }

    @Override
    public boolean test(String v) {
        return v.toLowerCase().contains(searchTerms);
    }

    public List<String> search2(String terms) {
        searchTerms = terms.toLowerCase();
        return books.stream()
            .filter(this)
            .toList();
    }

    public List<String> search(String terms) {
        final String t = terms.toLowerCase();
        return books.stream()
            .filter(v -> v.toLowerCase().contains(t))
            .toList();
    }
    
}
