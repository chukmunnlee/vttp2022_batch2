package vttp.paf.ticketing.controllers;

import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping(path="/purchase")
public class PurchaseController {

    @PostMapping
    public String postPurchase(@RequestBody MultiValueMap<String, String> form, 
            Model model) {
        // Retrieve the data from the form
        String name = form.getFirst("name");
        Integer numTickets = Integer.parseInt(form.getFirst("num_tickets"));

        // Generate a ticket order id
        String ticketOrder = UUID.randomUUID().toString().substring(0, 8);

        System.out.printf("Issuing %d tickets for %s\n", numTickets, name);

        model.addAttribute("ticketOrder", ticketOrder);
        model.addAttribute("name", name);
        model.addAttribute("tickets", numTickets);

        return "purchases";
    }
    
}
