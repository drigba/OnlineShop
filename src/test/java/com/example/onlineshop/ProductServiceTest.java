package com.example.onlineshop;

import com.example.onlineshop.dtos.ProductDTO;
import com.example.onlineshop.entity.Product;
import com.example.onlineshop.enums.ProductType;
import com.example.onlineshop.repository.ProductRepository;
import com.example.onlineshop.service.ProductService;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceTest {

    @Autowired
    private ProductService productService;

    @MockBean
    private ProductRepository productRepository;

    @BeforeEach
    public void init(){
        Product product = new Product();
        product.setId(1);
        product.setName("Kenyer");
        product.setPrice(1000);
        product.setProductType(ProductType.FOOD);
        product.setDescription("A kenyer jo.");
        product.setPopularity(64);
        //  when(productRepository.findById(1).thenReturn(Optional.of(product));
    }

    @Test
    public void createProduct(){
        ProductDTO productDTO = new ProductDTO();
        productDTO.setName("TV");
        productDTO.setPrice(23589115);
        productDTO.setProductType(ProductType.ELECTRONICS);

        assertThat(productService.createProduct(productDTO)).isEqualTo(productDTO);
    }

    @Test
    public void updateProduct(){
        ProductDTO productDTO = new ProductDTO();
        productDTO.setName("Kenyer");
        productDTO.setPrice(300);
        productDTO.setProductType(ProductType.FOOD);
        productDTO.setDescription("Az olcsobb kenyer jobb");
        productDTO.setPopularity(20);

        assertThat(productService.updateProduct(productDTO, 1)).isEqualTo(productDTO);
    }

    @Test
    public void deleteProduct()
    {
        // TODO
    }
}
