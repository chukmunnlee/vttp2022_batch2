package vttp2022.csf.day36.server.models;

import org.springframework.jdbc.support.rowset.SqlRowSet;

import jakarta.json.Json;
import jakarta.json.JsonObject;

public class Book {

    private String bookId;
    private String title;
    private String authors;
    private String description;

    public String getBookId() { return bookId; }
    public void setBookId(String bookId) { this.bookId = bookId; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getAuthors() { return authors; }
    public void setAuthors(String authors) { this.authors = authors; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    @Override
    public String toString() {
        return "Book[bookId=%s, title=%s]".formatted(bookId, title);
    }

    public JsonObject toBookSummary() {
        return Json.createObjectBuilder()
            .add("bookId", bookId)
            .add("title", title)
            .build();
    }

    public JsonObject toBook() {
        return Json.createObjectBuilder()
            .add("bookId", bookId)
            .add("title", title)
            .add("authors", authors)
            .add("description", description)
            .build();
    }

    public static Book create(SqlRowSet rs) {
        Book book = new Book();
        book.setBookId(rs.getString("book_id"));
        book.setTitle(rs.getString("title"));
        book.setAuthors(rs.getString("authors"));
        book.setDescription(rs.getString("description"));
        return book;
    }
}
