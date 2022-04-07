package com.example.onlineshop.controller;

import com.example.onlineshop.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/cart")
public class CartController {
    @Autowired
    private CartService cartService;
}
