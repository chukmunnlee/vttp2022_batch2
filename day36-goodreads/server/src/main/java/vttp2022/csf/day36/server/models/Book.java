package vttp2022.csf.day36.server.models;

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
}
