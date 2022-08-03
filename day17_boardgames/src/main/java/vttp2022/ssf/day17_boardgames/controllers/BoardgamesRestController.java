package vttp2022.ssf.day17_boardgames.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.support.Repositories;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import vttp2022.ssf.day17_boardgames.services.BoardgameService;

@RestController
@RequestMapping(path="/boardgames", produces=MediaType.APPLICATION_JSON_VALUE)
public class BoardgamesRestController {

    @Autowired
    private BoardgameService boardgameSvc;

    @GetMapping
    ResponseEntity<String> getBoardGames(
        @RequestParam(name="offset", defaultValue="0") Integer offset,
        @RequestParam(name="limit", defaultValue="5") Integer limit) {

        List<String> allKeys = boardgameSvc.keys().subList(offset, offset + limit);
        List<String> keyRange = allKeys.stream()
            .map(k -> "/boardgame/%s".formatted(k))
            .toList();

        JsonArray arr = Json.createArrayBuilder(keyRange).build();

        return ResponseEntity.ok(arr.toString());
    }

    @GetMapping(path="count")
    ResponseEntity<String> getBoardgamesCount() {

        Integer count = boardgameSvc.count();

        JsonObject payload = Json.createObjectBuilder()
                .add("count", count)
                .build();

        return ResponseEntity.ok(payload.toString());
    }
}
