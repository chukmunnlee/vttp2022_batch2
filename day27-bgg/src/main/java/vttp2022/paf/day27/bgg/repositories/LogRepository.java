package vttp2022.paf.day27.bgg.repositories;

import java.util.Date;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class LogRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    public void log(String q, Float score) {
        Document search = new Document();
        search.put("q", q);
        search.put("score", score);
        search.put("date", new Date());

        mongoTemplate.insert(search, "logs");
    }
    
}
