package vttp.paf.day23.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import vttp.paf.day23.models.BeerStyle;
import vttp.paf.day23.services.BeerService;

@Controller
@RequestMapping(path = {"/", "/index.html"})
public class IndexController {

    @Autowired
    private BeerService beerSvc;

    @GetMapping
    public String getIndex(Model model) {

        List<BeerStyle> styles = beerSvc.getBeerStyles();

        model.addAttribute("styles", styles);


        return "index";
    }
    
}
