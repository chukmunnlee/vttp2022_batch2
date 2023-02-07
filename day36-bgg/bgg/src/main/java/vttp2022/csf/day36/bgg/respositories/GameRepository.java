package vttp2022.csf.day36.bgg.respositories;

import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import vttp2022.csf.day36.bgg.models.Game;

@Repository
public class GameRepository {

    @Autowired
    private MongoTemplate template;

    public List<Game> getGames(int limit, int skip) {
        Query query = (new Query()).limit(limit).skip(skip);
        return template.find(query, Document.class, "games")
            .stream()
            .map(v -> { return Game.create(v); })
            .toList();
    }
    
}
