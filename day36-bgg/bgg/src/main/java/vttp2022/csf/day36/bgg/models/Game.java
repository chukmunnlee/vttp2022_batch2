package vttp2022.csf.day36.bgg.models;

import org.bson.Document;

import jakarta.json.Json;
import jakarta.json.JsonObject;

public class Game {
    private int gameId;
    private String name;

    public int getGameId() { return gameId; }
    public void setGameId(int gameId) { this.gameId = gameId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public JsonObject toJson() {
        return Json.createObjectBuilder()
            .add("gameId", gameId)
            .add("name", name)
            .build();
    }

    public static Game create(Document doc) {
        Game game = new Game();
        game.setGameId(doc.getInteger("gid"));
        game.setName(doc.getString("name"));
        return game;
    }
}
