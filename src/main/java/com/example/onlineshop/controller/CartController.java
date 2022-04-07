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
    public CartDTO addProductToCart(@RequestBody ProductDTO productDTO,@RequestBody CartDTO cartDTO){
        return cartService.addProductToCart(productDTO, cartDTO);
    }

    @PostMapping(path="/deleteproduct")
    public CartDTO deleteProductFromCart(@RequestBody ProductDTO productDTO,@RequestBody CartDTO cartDTO){
        return cartService.deleteProductFromCart(productDTO, cartDTO);
    }

    @GetMapping(path="/getcontent")
    public Map<ProductDTO, Long> getCartContent(@RequestBody CartDTO cartDTO) {
        return cartService.getCartContent(cartDTO);
    }

    @PostMapping(path="/clearcart")
    public void clearCart(@RequestBody CartDTO cartDTO) {
        cartService.deleteAllProductsFromCart(cartDTO);
    }
}
