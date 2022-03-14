package com.example.onlineshop.controller;

import com.example.onlineshop.dtos.ProductDTO;
import com.example.onlineshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping(path="/save")
    public ProductDTO createProduct(@RequestBody ProductDTO productDTO){
        return productService.createProduct(productDTO);
    }

    @GetMapping(path="/getall")
    public List<ProductDTO> getAllProducts(){
        return productService.getAllProducts();
    }
}
