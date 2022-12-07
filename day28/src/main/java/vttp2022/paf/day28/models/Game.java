package vttp2022.paf.day28.models;

import java.util.LinkedList;
import java.util.List;

import org.bson.Document;

public class Game {

    private String name;
    private String image;
    private List<Comment> comments = new LinkedList<>();

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getImage() { return image; }
    public void setImage(String image) { this.image = image; }

    public List<Comment> getComments() { return comments; }
    public void setComments(List<Comment> comments) { this.comments = comments; }

    @Override
    public String toString() {
        return "name: %s, image: %s, comments: %s".formatted(name, image, comments);
    }

    public static Game create(Document d) {
        System.out.println(">>>> " + d.toJson());
        Document id = d.get("_id", Document.class);
        Game g = new Game();
        g.setName(id.getString("name"));
        g.setImage(id.getString("image"));
        return g;
    }
    
}
