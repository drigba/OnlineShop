package com.example.onlineshop;

import com.example.onlineshop.repository.ProductRepository;
import com.example.onlineshop.service.ProductService;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

public class TestEnvContainer {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public ProductService productService(){return new ProductService();}

    /*
    @Bean
    public ProductRepository productRepository(){}
     */


}
