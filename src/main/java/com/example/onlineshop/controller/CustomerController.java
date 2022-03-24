package com.example.onlineshop.controller;

import com.example.onlineshop.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;
}
