package com.example.onlineshop.controller;

import com.example.onlineshop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/order")
public class OrderController {
    @Autowired
    private OrderService orderService;
}