package vttp2022.csf.day36.bgg.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vttp2022.csf.day36.bgg.models.Comment;
import vttp2022.csf.day36.bgg.models.Game;
import vttp2022.csf.day36.bgg.respositories.CommentRepository;
import vttp2022.csf.day36.bgg.respositories.GameRepository;

@Service
public class BggService {

    @Autowired
    private GameRepository gameRepo;

    @Autowired
    private CommentRepository commentRepo;

    public List<Comment> getComments(int gameId) {
        return commentRepo.findCommentByGameId(gameId);
    }

    public List<Game> getGames() {
        return this.getGames(20, 0);
    }
    public List<Game> getGames(int limit, int skip) {
        return gameRepo.getGames(limit, skip);
    }
    
}
