package com.example.onlineshop.service;

import com.example.onlineshop.dtos.CartDTO;
import com.example.onlineshop.dtos.ProductDTO;
import com.example.onlineshop.entity.Cart;
import com.example.onlineshop.mapper.CartMapper;
import com.example.onlineshop.repository.CartRepository;
import com.example.onlineshop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
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

    @Autowired
    private ProductService productService;

    private Cart dtoToCart(CartDTO cartDTO){
        return cartMapper.dtoToEntity(cartDTO);
    }

    private CartDTO cartToDTO(Cart cart){
        return CartDTO.builder()
                .id(cart.getId())
                .products(cart.getProducts())
                .sumPrice(cart.getSumPrice())
                .build();
    }

    public CartDTO addProductToCart(ProductDTO productDTO,Integer id){
        Cart cart = cartRepository.getById(id);
        cart.getProducts().add(productRepository.findByNameAndPriceAndDescription(productDTO.getName(), productDTO.getPrice(), productDTO.getDescription()));
        cartRepository.save(cart);
        return cartToDTO(cart);
    }

    public CartDTO deleteProductFromCart(ProductDTO productDTO,Integer id){
        Cart cart = cartRepository.getById(id);
        cart.getProducts().remove(productRepository.findByNameAndPriceAndDescription(productDTO.getName(), productDTO.getPrice(), productDTO.getDescription()));
        cartRepository.save(cart);
        return cartToDTO(cart);
    }

    public Map<ProductDTO, Long> getCartContent(Integer id){
        Cart cart = cartRepository.getById(id);
        return productService.getSomeProducts(cart.getProducts())
                .stream()
                .collect(
                        Collectors.groupingBy(ProductDTO ->ProductDTO, Collectors.counting()));
    }

    public void deleteAllProductsFromCart(Integer id){
        Cart cart = cartRepository.getById(id);
        cart.getProducts().clear();
        cart.setSumPrice(0);
        cartRepository.save(cart);
    }

    public CartDTO getCart(Integer id){
        return cartToDTO(cartRepository.getById(id));
    }

}
