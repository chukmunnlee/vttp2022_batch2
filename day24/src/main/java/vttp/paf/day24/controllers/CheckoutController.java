package vttp.paf.day24.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import vttp.paf.day24.models.LineItem;

@Controller
@RequestMapping(path="/checkout")
public class CheckoutController {

    @PostMapping
    public String postCheckout(Model model, HttpSession sess) {

        List<LineItem> lineItems = (List<LineItem>)sess.getAttribute("cart");

        // Destroy the session
        sess.invalidate();

        model.addAttribute("total", lineItems.size() * 2.5);

        return "checkout";
    }
    
}
