package com.example.onlineshop;

import com.example.onlineshop.entity.Order;
import com.example.onlineshop.mapper.CartMapper;
import com.example.onlineshop.mapper.OrderMapper;
import com.example.onlineshop.mapper.ProductMapper;
import com.example.onlineshop.mapper.RetailerMapper;
import com.example.onlineshop.repository.OrderRepository;
import com.example.onlineshop.repository.ProductRepository;
import com.example.onlineshop.service.CartService;
import com.example.onlineshop.service.ProductService;
import com.example.onlineshop.service.OrderService;
import com.example.onlineshop.service.RetailerService;
import org.mockito.Mockito;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
//@ComponentScan()
public class TestEnvContainer {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public ProductRepository productRepository(){
        ProductRepository mockproductRepository = Mockito.mock(ProductRepository.class);
        return mockproductRepository;
    }

   /* @Bean
    public OrderRepository orderRepository(){
        OrderRepository mockorderRepository = Mockito.mock(OrderRepository.class);
        return mockorderRepository;
    }*/

    @Bean
    public ProductService productService(){return new ProductService();}

    @Bean
    public ProductMapper productMapper(){
        ProductMapper mockproductMapper = Mockito.mock(ProductMapper.class);
        return mockproductMapper;
    }

    @Bean
    public OrderMapper orderMapper(){
        OrderMapper mockorderMapper = Mockito.mock(OrderMapper.class);
        return mockorderMapper;
    }

    @Bean
    public CartMapper cartMapper(){
        CartMapper mockcartMapper = Mockito.mock(CartMapper.class);
        return mockcartMapper;
    }

    @Bean
    public RetailerMapper retailerMapper(){
        RetailerMapper mockretailerMapper = Mockito.mock(RetailerMapper.class);
        return mockretailerMapper;
    }

    @Bean
    public OrderService orderService() {
        return new OrderService();
    }

    @Bean
    public CartService cartService(){return new CartService();}

    @Bean
    public RetailerService retailerService(){return new RetailerService();}



}
