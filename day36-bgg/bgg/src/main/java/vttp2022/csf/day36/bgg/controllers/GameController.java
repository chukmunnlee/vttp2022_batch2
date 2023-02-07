package vttp2022.csf.day36.bgg.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;
import vttp2022.csf.day36.bgg.models.Comment;
import vttp2022.csf.day36.bgg.models.Game;
import vttp2022.csf.day36.bgg.services.BggService;

@Controller
@RequestMapping(path="/api")
public class GameController {

    @Autowired
    private BggService bggSvc;

    @GetMapping(path="/game/{gameId}/comments")
    @ResponseBody
    public ResponseEntity<String> getComments(@PathVariable int gameId) {

        List<Comment> comments = bggSvc.getComments(gameId);
        JsonArrayBuilder arrBuilder = Json.createArrayBuilder();
        comments.stream()
            .forEach(v -> {
                arrBuilder.add(v.toJson());
            });

        return ResponseEntity.ok(arrBuilder.build().toString());
    }

    @GetMapping(path="/games")
    @ResponseBody
    public ResponseEntity<String> getGames(
            @RequestParam(defaultValue="20") int limit, @RequestParam(defaultValue="0") int offset) {

        List<Game> games = bggSvc.getGames(limit, offset);
        JsonArrayBuilder arrBuilder = Json.createArrayBuilder();
        games.stream()
            .forEach(v -> {
                arrBuilder.add(v.toJson());
            });

        return ResponseEntity.ok(arrBuilder.build().toString());
    }
}
