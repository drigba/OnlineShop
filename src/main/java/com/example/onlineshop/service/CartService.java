package com.example.onlineshop.service;

import com.example.onlineshop.dtos.CartDTO;
import com.example.onlineshop.dtos.ProductDTO;
import com.example.onlineshop.entity.Cart;
import com.example.onlineshop.mapper.CartMapper;
import com.example.onlineshop.repository.CartRepository;
import com.example.onlineshop.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
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
        return Cart.builder()
                .id(cartDTO.getId())
                .products(cartDTO.getProducts())
                .sumPrice(cartDTO.getSumPrice())
                .build();
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
        log.info("Product ( "+ productDTO.getName() +") added to cart(" + id + ").");
        return cartToDTO(cart);
    }

    public CartDTO deleteProductFromCart(ProductDTO productDTO,Integer id){
        Cart cart = cartRepository.getById(id);
        cart.getProducts().remove(productRepository.findByNameAndPriceAndDescription(productDTO.getName(), productDTO.getPrice(), productDTO.getDescription()));
        cartRepository.save(cart);
        log.info("Product ( "+ productDTO.getName() +") deleted from cart(" + id + ").");
        return cartToDTO(cart);
    }


    public Map<Integer, Long> getCartContent(Integer id){
        Cart cart = cartRepository.getById(id);
        List<ProductDTO> products= productService.getSomeProducts(cart.getProducts());
        Map<Integer, Long> content = new HashMap<>();
        List<Integer> uniqueProducts = new ArrayList<>();
        List<Long> number = new ArrayList<>();
        if(products.size() != 0){
            uniqueProducts.add(products.get(0).getId());
            number.add(1L);
            products.forEach(productDTO -> {
                if(!uniqueProducts.contains(productDTO.getId())){
                    uniqueProducts.add(productDTO.getId());
                    number.add(1L);
                } else {
                    Long num = number.get(uniqueProducts.indexOf(productDTO.getId()));
                    num++;
                    log.info(String.valueOf(num));
                    number.set(uniqueProducts.indexOf(productDTO.getId()), num);
                }
            });
            for (int i = 0; i < uniqueProducts.size(); i++) {
                content.put(uniqueProducts.get(i), number.get(i));
            }
        }
        log.info(String.valueOf(content));

        return content;
    }

    public void deleteAllProductsFromCart(Integer id){
        Cart cart = cartRepository.getById(id);
        log.info(String.valueOf(cartRepository.getById(id).getProducts()));

        cart.getProducts().clear();
        cart.setSumPrice(0);
        cartRepository.save(cart);
        log.info(String.valueOf(cartRepository.getById(id).getProducts()));
        log.info("All products deleted from cart(" + id + ").");
    }

    public CartDTO getCart(Integer id){
        return cartToDTO(cartRepository.getById(id));
    }

}
