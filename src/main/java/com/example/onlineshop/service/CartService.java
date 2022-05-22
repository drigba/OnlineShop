package com.example.onlineshop.service;

import com.example.onlineshop.dtos.CartDTO;
import com.example.onlineshop.dtos.ProductDTO;
import com.example.onlineshop.entity.Cart;
import com.example.onlineshop.mapper.CartMapper;
import com.example.onlineshop.repository.CartRepository;
import com.example.onlineshop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CartService {
    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CartMapper cartMapper;

    @Autowired
    private ProductService productService;

    private Cart dtoToCart(CartDTO cartDTO){
        return cartMapper.dtoToEntity(cartDTO);
    }

    private CartDTO cartToDTO(Cart cart){
        return CartDTO.builder()
                .products(cart.getProducts())
                .sumPrice(cart.getSumPrice())
                .build();
    }

    public CartDTO addProductToCart(ProductDTO productDTO, CartDTO cartDTO){
        Cart cart = dtoToCart(cartDTO);
        cart.getProducts().add(productRepository.findByNameAndPriceAndDescription(productDTO.getName(), productDTO.getPrice(), productDTO.getDescription()));
        cartRepository.save(cart);
        return cartToDTO(cart);
    }

    public CartDTO deleteProductFromCart(ProductDTO productDTO, CartDTO cartDTO){
        Cart cart = dtoToCart(cartDTO);
        cart.getProducts().remove(productRepository.findByNameAndPriceAndDescription(productDTO.getName(), productDTO.getPrice(), productDTO.getDescription()));
        cartRepository.save(cart);
        return cartToDTO(cart);
    }

    public Map<ProductDTO, Long> getCartContent(CartDTO cartDTO){
        Cart cart = dtoToCart(cartDTO);
        List<ProductDTO> products= productService.getSomeProducts(cart.getProducts());
        Map<ProductDTO, Long> content = new HashMap<>();
        List<ProductDTO> uniqueProducts = new ArrayList<>();
        List<Long> number = new ArrayList<>();
        uniqueProducts.add(products.get(0));
        number.add(1L);
        products.forEach(productDTO -> {
            if(!uniqueProducts.contains(productDTO)){
                uniqueProducts.add(productDTO);
                number.add(1L);
            } else {
                Long num = number.get(uniqueProducts.indexOf(productDTO));
                num++;
                number.set(uniqueProducts.indexOf(productDTO), num);
            }
        });
        for (int i = 0; i < uniqueProducts.size(); i++) {
            content.put(uniqueProducts.get(i), number.get(i));
        }
        return content;
    }

    public void deleteAllProductsFromCart(CartDTO cartDTO){
        Cart cart = dtoToCart(cartDTO);
        cart.getProducts().clear();
        cart.setSumPrice(0);
        cartRepository.save(cart);
    }
}
