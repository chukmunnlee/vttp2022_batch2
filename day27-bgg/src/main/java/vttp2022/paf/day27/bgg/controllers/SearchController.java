package vttp2022.paf.day27.bgg.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import vttp2022.paf.day27.bgg.models.Comment;
import vttp2022.paf.day27.bgg.services.SearchService;


@Controller
@RequestMapping(path="/search")
public class SearchController {

    @Autowired
    private SearchService searchSvc;

    @GetMapping
    public String getSearch(Model model,
        @RequestParam String q, @RequestParam Float score) {

        System.out.printf("q=%s, score=%f\n", q, score);

        List<Comment> results = searchSvc.search(q, score, 20, 0);

        model.addAttribute("q", q);
        model.addAttribute("score", score);
        model.addAttribute("results", results);

        return "search-result";
    }
    
}
