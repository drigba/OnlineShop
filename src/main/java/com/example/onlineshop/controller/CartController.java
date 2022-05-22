package com.example.onlineshop.controller;

import com.example.onlineshop.dtos.CartDTO;
import com.example.onlineshop.dtos.ProductDTO;
import com.example.onlineshop.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(path="/cart")
public class CartController {
    @Autowired
    private CartService cartService;

    @PostMapping(path="/addproduct")
    public CartDTO addProductToCart(@RequestBody ProductDTO productDTO,@RequestParam Integer id){
        return cartService.addProductToCart(productDTO, id);
    }

    @PostMapping(path="/deleteproduct")
    public CartDTO deleteProductFromCart(@RequestBody ProductDTO productDTO,@RequestParam Integer id){
        return cartService.deleteProductFromCart(productDTO, id);
    }

    @GetMapping(path="/getcontent")
    public Map<ProductDTO, Long> getCartContent(@RequestParam Integer id) {
        return cartService.getCartContent(id);
    }

    @PostMapping(path="/clearcart")
    public void clearCart(@RequestParam Integer id) {
        cartService.deleteAllProductsFromCart(id);
    }
}
