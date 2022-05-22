package com.example.onlineshop.controller;

import com.example.onlineshop.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class LoginController {
    @Autowired
    private CustomerService customerService;

    @RequestMapping(value="/login",method = RequestMethod.POST)
    public String Login(@RequestBody Pair<String, String> body){
        return customerService.authenticateCustomer(body.getFirst(),body.getSecond());

    }


}
