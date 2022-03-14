package com.example.onlineshop;

import com.example.onlineshop.dtos.ProductDTO;
import com.example.onlineshop.enums.ProductType;
import com.example.onlineshop.service.ProductService;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceTest {

    @Autowired
    private ProductService productService;

    @BeforeEach
    public void init(){

    }

    @Test
    public void createProduct(){
        ProductDTO productDTO = new ProductDTO();
        productDTO.setName("TV");
        productDTO.setPrice(23589115);
        productDTO.setProductType(ProductType.ELECTRONICS);

        assertThat(productService.createProduct(productDTO)).isEqualTo(productDTO);
    }
}
