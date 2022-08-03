package vttp2022.ssf.day17_boardgames.repositories;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

@Repository
public class BoardgameRepository {

    @Autowired @Qualifier("redislab")
    private RedisTemplate<String, String> redisTemplate;

    public Integer count() {
        Set<String> keys = redisTemplate.keys("[0-9]*");
        return keys.size();
    }
    public List<String> keys() {
        Set<String> keys = redisTemplate.keys("[0-9]*");
        List<String> result = new LinkedList<>(keys);
        return result.stream()
                .map(v -> Integer.parseInt(v))
                .sorted()
                .map(v -> v.toString())
                .toList();
    }

    public String get(String id) {
        ValueOperations<String, String> valueOps = redisTemplate.opsForValue();
        return valueOps.get(id);
    }
    
}
