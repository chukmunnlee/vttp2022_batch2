package vttp2022.paf.day27.bgg.models;

import org.bson.Document;

public class Comment {

    private String user;
    private String text;
    private Integer rating;
    private Float score;

    public String getUser() { return user; }
    public void setUser(String user) { this.user = user; }

    public String getText() { return text; }
    public void setText(String text) { this.text = text; }

    public Integer getRating() { return rating; }
    public void setRating(Integer rating) { this.rating = rating; }

    public Float getScore() { return score; }
    public void setScore(Float score) { this.score = score; }

    @Override
    public String toString() {
        return "Comment [user=" + user + ", text=" + text + ", rating=" + rating + ", score=" + score + "]";
    }

    public static Comment create(Document d) {
        Comment c = new Comment();
        c.setUser(d.getString("user"));
        c.setText(d.getString("c_text"));
        c.setRating(d.getInteger("rating"));
        c.setScore(d.getDouble("score").floatValue());
        return c;
    }
}
