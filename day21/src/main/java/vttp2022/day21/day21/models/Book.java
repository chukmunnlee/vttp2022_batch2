package vttp2022.day21.day21.models;

import org.springframework.jdbc.support.rowset.SqlRowSet;

import jakarta.json.Json;
import jakarta.json.JsonObject;

public class Book {

    private String bookId;
    private String title;
    private String description;
    private Float rating;
    private String image;

    public String getBookId() { return bookId; }
    public void setBookId(String bookId) { this.bookId = bookId; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Float getRating() { return rating; }
    public void setRating(Float rating) { this.rating = rating; }

    public String getImage() { return image; }
    public void setImage(String image) { this.image = image; }

    public static Book create(SqlRowSet rs) {
        Book b = new Book();
        b.setBookId(rs.getString("book_id"));
        b.setTitle(rs.getString("title"));
        b.setDescription(rs.getString("description"));
        b.setRating(rs.getFloat("rating"));
        b.setImage(rs.getString("image_url"));
        return b;
    }

    public JsonObject toJSON() {
        return Json.createObjectBuilder()
            .add("bookId", getBookId())
            .add("title", getTitle())
            .add("description", getDescription())
            .add("rating", getRating())
            .add("image", getImage())
            .build();
    }

}
