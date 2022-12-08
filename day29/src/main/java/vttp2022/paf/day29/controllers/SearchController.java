package vttp2022.paf.day29.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import vttp2022.paf.day29.models.SuperHero;
import vttp2022.paf.day29.respositories.MarvelCache;
import vttp2022.paf.day29.services.MarvelService;

@Controller
@RequestMapping(path="/search")
public class SearchController {

    @Autowired
    private MarvelService marvelSvc;

    @Autowired
    private MarvelCache marvelCache;

    @GetMapping
    public String search(@RequestParam String heroName, Model model) {

        List<SuperHero> results = null;

        Optional<List<SuperHero>> opt = marvelCache.get(heroName);
        if (opt.isEmpty()) {
            results = marvelSvc.search(heroName);
            marvelCache.cache(heroName, results);
        } else  { 
            results = opt.get();
            System.out.printf(">>>> from CACHE\n");
        }

        model.addAttribute("results", results);

        return "result";
    }
    
}
