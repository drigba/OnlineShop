package com.example.onlineshop.controller;

import com.example.onlineshop.service.RetailerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/retailer")
public class RetailerController {
    @Autowired
    private RetailerService retailerService;
}
