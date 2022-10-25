package com.example.cart.controllers;

import java.util.logging.Logger;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.cart.models.Cart;
import com.example.cart.models.Item;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
@RequestMapping
public class CartController {

	@Autowired
	private Cart cart;

	private Logger logger = Logger.getLogger(CartController.class.getName());

	@GetMapping(path = { "/", "/index.html", "/cart" })
	public String getCart(Model model) {
		logger.info("Cart: %s\n".formatted(cart.getCreatedDateTimestamp()));
		model.addAttribute("cart", cart);
		return "index";
	}

	@PostMapping(value="/cart")
	public String postCart(@RequestBody MultiValueMap<String, String> form, Model model) {

		logger.info("Cart: %s\n".formatted(cart.getCreatedDateTimestamp()));

		String name = form.getFirst("name");
		Integer quantity = Integer.parseInt(form.getFirst("quantity"));

		Item item = new Item();
		item.setName(name);
		item.setQuantity(quantity);
		cart.addItem(item);

		model.addAttribute("cart", cart);

		return "index";
	}

	@PostMapping(value="/cart/checkout")
	public String postCartCheckout(HttpSession session) {

		logger.info("Checkout");
		for (Item item: cart.getContents()) {
			logger.info("\tname: %s, quantity: %d".formatted(item.getName(), item.getQuantity()));
		}

		session.invalidate();

		return "redirect:/";
	}
	
}
