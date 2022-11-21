package vttp2022.day21.day21.repositories;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import vttp2022.day21.day21.models.Book;

import static vttp2022.day21.day21.repositories.Queries.*;

@Repository
public class BookRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Book> getBooksByRating(Float rating) {

        // Perform the query
        final SqlRowSet rs = jdbcTemplate.queryForRowSet(
                SQL_SELECT_BOOKS_BY_RATING, rating);

        final List<Book> books = new LinkedList<>();

        // Attempt to move the cursor to the next row
        while (rs.next())
            // We have a record
            books.add(Book.create(rs));

        return books;
    }
    
    public List<Book> getBooksByTitle(String bookName, Integer limit) {

        // Perform the query
        final SqlRowSet rs = jdbcTemplate.queryForRowSet(SQL_SELECT_BOOKS_BY_TITLE,
            "%%%s%%".formatted(bookName), limit);

        final List<Book> results = new LinkedList<>();
        while (rs.next())
            results.add(Book.create(rs));
        
        return results;
    }
}
