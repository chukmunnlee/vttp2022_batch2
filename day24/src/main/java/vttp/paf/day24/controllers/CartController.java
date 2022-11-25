package vttp.paf.day24.controllers;

import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;

import vttp.paf.day24.models.LineItem;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
@RequestMapping(path="/cart")
public class CartController {

    @PostMapping
    public String postCart(@RequestBody MultiValueMap<String, String> form
            , Model model, HttpSession sess) {

        List<LineItem> lineItems = (List<LineItem>)sess.getAttribute("cart");
        if (null == lineItems) {
            System.out.println("This is a new session");
            System.out.printf("session id = %s\n", sess.getId());
            lineItems = new LinkedList<>();
            sess.setAttribute("cart", lineItems);
        }

        String item = form.getFirst("item");
        Integer quantity  = Integer.parseInt(form.getFirst("quantity"));
        lineItems.add(new LineItem(item, quantity));

        for (LineItem li: lineItems)
            System.out.printf("description: %s, quantity: %d\n", li.getDescription(), li.getQuantity());

        model.addAttribute("lineItems", lineItems);
        
        return "cart_template";
    }
    
    
}
