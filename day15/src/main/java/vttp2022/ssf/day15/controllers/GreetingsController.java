package vttp2022.ssf.day15.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(path={"/greetings"})
public class GreetingsController {

    @GetMapping(path = {"/{name}"})
    public String getGreetingsWithName(@PathVariable String name, Model model) {
        model.addAttribute("name", name);
        return "greetings";
    }

    @GetMapping
    public String getGreethings(@RequestParam String name, Model model) {
        model.addAttribute("name", name);
        return "greetings";
    }

    @PostMapping
    public String postGreetings(@RequestBody MultiValueMap<String, String> form, 
            Model model) {
        String name = form.getFirst("name");
        model.addAttribute("name", name);
        return "greetings";
    }
    
}
