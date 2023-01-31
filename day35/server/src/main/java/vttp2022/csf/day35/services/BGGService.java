package vttp2022.csf.day35.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vttp2022.csf.day35.models.Game;
import vttp2022.csf.day35.respositories.GameRepository;

@Service
public class BGGService {

    @Autowired
    private GameRepository gameRepo;

    public List<Game> findGameByName(String name) {
        return gameRepo.findGamesByName("%%%s%%".formatted(name));
    }
    
}
