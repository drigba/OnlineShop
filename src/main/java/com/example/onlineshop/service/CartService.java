package com.example.onlineshop.service;

import com.example.onlineshop.dtos.CartDTO;
import com.example.onlineshop.dtos.ProductDTO;
import com.example.onlineshop.entity.Cart;
import com.example.onlineshop.entity.Product;
import com.example.onlineshop.mapper.CartMapper;
import com.example.onlineshop.repository.CartRepository;
import com.example.onlineshop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class CartService {
    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CartMapper cartMapper;

    private Cart dtoToCart(CartDTO cartDTO){
        return cartMapper.dtoToEntity(cartDTO);
    }

    private CartDTO cartToDTO(Cart cart){
        return CartDTO.builder()
                .products(cart.getProducts())
                .sumPrice(cart.getSumPrice())
                .build();
    }

    public void addProductToCart(ProductDTO productDTO, CartDTO cartDTO){
        Cart cart = dtoToCart(cartDTO);
        cart.getProducts().add(productRepository.findByNameAndPriceAndDescription(productDTO.getName(), productDTO.getPrice(), productDTO.getDescription()));
        cartRepository.save(cart);
    }

    public void deleteProductFromCart(ProductDTO productDTO, CartDTO cartDTO){
        Cart cart = dtoToCart(cartDTO);
        cart.getProducts().remove(productRepository.findByNameAndPriceAndDescription(productDTO.getName(), productDTO.getPrice(), productDTO.getDescription()));
        cartRepository.save(cart);
    }

    public Map<Product, Long> getCartContent(CartDTO cartDTO){
        Cart cart = dtoToCart(cartDTO);
        return cart.getProducts().stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }

    public void deleteAllProductsFromCart(CartDTO cartDTO){
        Cart cart = dtoToCart(cartDTO);
        cart.getProducts().clear();
        cartRepository.save(cart);
    }
}