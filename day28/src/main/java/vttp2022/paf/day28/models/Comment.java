package vttp2022.paf.day28.models;

import org.bson.Document;

public class Comment {

    private String user;
    private Integer rating;
    private String text;

    public String getUser() { return user; }
    public void setUser(String user) { this.user = user; }

    public Integer getRating() { return rating; }
    public void setRating(Integer rating) { this.rating = rating; }

    public String getText() { return text; }
    public void setText(String text) { this.text = text; }

    @Override
    public String toString() {
        return "user: %s, rating: %d, text: %s".formatted(user, rating, text);
    }

    public static Comment create(Document d) {
        Comment c = new Comment();
        c.setUser(d.getString("user"));
        c.setRating(d.getInteger("rating"));
        c.setText(d.getString("c_text"));
        return c;
    }
    
}
