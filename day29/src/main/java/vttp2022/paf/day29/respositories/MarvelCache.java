package vttp2022.paf.day29.respositories;

import java.io.StringReader;
import java.time.Duration;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonReader;
import jakarta.json.JsonObject;

import vttp2022.paf.day29.AppConfig;
import vttp2022.paf.day29.models.SuperHero;

@Repository
public class MarvelCache {

    @Autowired @Qualifier(AppConfig.CACHE_MARVEL)
    private RedisTemplate<String, String> redisTemplate;

    public void cache(String key, List<SuperHero> values) {

        ValueOperations<String, String> ops = redisTemplate.opsForValue();

        JsonArrayBuilder arrBuilder = Json.createArrayBuilder();
        values.stream()
            .forEach(c -> { 
                arrBuilder.add(c.toJson());
            });
        ops.set(key, arrBuilder.build().toString(), Duration.ofSeconds(300));
    }

    public Optional<List<SuperHero>> get(String name) {

        ValueOperations<String, String> ops = redisTemplate.opsForValue();
        String value = ops.get(name);
        if (null == value)
            return Optional.empty();

        JsonReader reader = Json.createReader(new StringReader(value));
        JsonArray results = reader.readArray();

        List<SuperHero> heros = results.stream()
                .map(v -> (JsonObject)v)
                .map(v -> SuperHero.fromCache(v))
                .toList();

        return Optional.of(heros);
    }
    
}
