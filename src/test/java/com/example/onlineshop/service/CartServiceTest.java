package com.example.onlineshop.service;

import com.example.onlineshop.TestEnvContainer;
import com.example.onlineshop.dtos.CartDTO;
import com.example.onlineshop.dtos.OrderDTO;
import com.example.onlineshop.dtos.ProductDTO;
import com.example.onlineshop.entity.Cart;
import com.example.onlineshop.entity.Product;
import com.example.onlineshop.enums.OrderStatus;
import com.example.onlineshop.enums.ProductType;
import com.example.onlineshop.mapper.CartMapper;
import com.example.onlineshop.repository.CartRepository;
import com.example.onlineshop.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = TestEnvContainer.class)
class CartServiceTest {

    @Autowired
    private CartService cartService;

    @MockBean
    private CartRepository cartRepository;

    @MockBean
    private ProductRepository productRepository;

    @MockBean
    private OrderService orderService;

    @MockBean
    private ProductService productService;



    private CartDTO cartDTO = new CartDTO();
    List<Product> _prod = new ArrayList<>();
    Product _product = new Product(1,"termek1",200, ProductType.ELECTRONICS,"t1_description",20);
    Cart cart = new Cart();
    ProductDTO productDTO = new ProductDTO();
    Map<ProductDTO,Long> _map = new HashMap<>();
    List<ProductDTO> _prodDTOlist = new ArrayList<>();

    @BeforeEach
    void setUp() {
        _prod.add(_product);
        //_prod.add(new Product(2,"termek2",400,ProductType.FOOD,"t2_description",30));
        cartDTO.setProducts(_prod);
        cartDTO.setSumPrice(600);
        cart.setProducts(_prod);
        cart.setSumPrice(600);


        productDTO.setName("termek1");
        productDTO.setPrice(200);
        productDTO.setProductType(ProductType.ELECTRONICS);
        productDTO.setDescription("t1_description");
        productDTO.setPopularity(20);

        when(cartRepository.getById(1)).thenReturn(cart);

        _map.put(productDTO,1L);
        _prodDTOlist.add(productDTO);

    }

    @Test
    void addProductToCart() {
        when(this.productRepository.findByNameAndPriceAndDescription(productDTO.getName(),productDTO.getPrice(),productDTO.getDescription())).thenReturn(_product);
        when(this.cartRepository.save(cart)).thenReturn(cart);
        assertThat(this.cartService.addProductToCart(productDTO,1)).isEqualTo(cartDTO);
    }

    @Test
    void deleteProductFromCart() {
        when(this.productRepository.findByNameAndPriceAndDescription(productDTO.getName(),productDTO.getPrice(),productDTO.getDescription())).thenReturn(_product);
        when(this.cartRepository.save(cart)).thenReturn(cart);
        assertThat(this.cartService.deleteProductFromCart(productDTO,1)).isEqualTo(cartDTO);

    }

    @Test
    void getCartContent() {
        when(this.productService.getSomeProducts(cart.getProducts())).thenReturn(_prodDTOlist);
        assertThat(this.cartService.getCartContent(1)).isEqualTo(_map);
    }

    @Test
    void deleteAllProductsFromCart() {
        //hen(this.cartRepository.save(cart)).thenReturn(cart);

    }
}