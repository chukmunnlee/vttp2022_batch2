package vttp2022.paf.day29.models;

import jakarta.json.JsonArray;
import jakarta.json.JsonObject;

public class SuperHero {

    private Integer id;
    private String name;
    private String description;
    private String image;
    private String details;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getImage() { return image; }
    public void setImage(String image) { this.image = image; }

    public String getDetails() { return details; }
    public void setDetails(String details) { this.details = details; }

    @Override
    public String toString() {
        return "SuperHero {id=%d, name=%s, description=%s, image=%s, details=%s}"
                .formatted(id, name, description, image, details);
    }

    public static SuperHero create(JsonObject doc) {
        final SuperHero sh = new SuperHero();

        sh.setId(doc.getInt("id"));
        sh.setName(doc.getString("name"));
        sh.setDescription(doc.getString("description").trim().length() > 0? 
                doc.getString("description"): "No description");

        JsonObject img = doc.getJsonObject("thumbnail");
        sh.setImage("%s.%s".formatted(img.getString("path"), img.getString("extension")));

        JsonArray urls = doc.getJsonArray("urls");
        for (Integer i = 0; i < urls.size(); i++) {
            JsonObject d = urls.getJsonObject(i);
            if (d.getString("type").equals("detail")) {
                sh.setDetails(d.getString("url"));
                break;
            }
        }

        return sh;
    }

}
