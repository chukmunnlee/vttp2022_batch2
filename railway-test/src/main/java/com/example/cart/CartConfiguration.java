package com.example.cart;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.web.context.WebApplicationContext;

import com.example.cart.models.Cart;

@Configuration
public class CartConfiguration {

    private Logger logger = Logger.getLogger(CartConfiguration.class.getName());

    @Bean
    @Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
    public Cart cart() {
        logger.info("Creating Cart for session");
        return new Cart();
    }
}
