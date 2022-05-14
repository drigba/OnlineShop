package com.example.onlineshop;

import com.example.onlineshop.dtos.ProductDTO;
import com.example.onlineshop.entity.Product;
import com.example.onlineshop.enums.ProductType;
import com.example.onlineshop.repository.ProductRepository;
import com.example.onlineshop.service.ProductService;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
@WebMvcTest(ProductService.class)
@ContextConfiguration(classes = TestEnvContainer.class)
public class ProductServiceTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ProductRepository productRepository;

    @MockBean
    private ProductService productService;
    

    @BeforeEach
    public void init(){
        Product product = new Product();
        product.setId(1);
        product.setName("Kenyer");
        product.setPrice(1000);
        product.setProductType(ProductType.FOOD);
        product.setDescription("A kenyer jo.");
        product.setPopularity(64);
        when(productRepository.getById(1)).thenReturn(product);
    }

    @Test
    public void productService_not_null(){
        Assert.notNull(productService,"productService not null");
    }

    @Test
    public void productRepository_not_null(){
        System.out.println("shitass: " + productRepository.getById(1).getName());
        Assert.notNull(productRepository,"productRepository not null");
    }

    @Test
    public void createProduct(){
        ProductDTO productDTO = new ProductDTO();
        productDTO.setName("TV");
        productDTO.setPrice(23589115);
        productDTO.setProductType(ProductType.ELECTRONICS);

        System.out.println("productDTO: " + productDTO.getName());
        Assert.notNull(productDTO,"productDTo not null");


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

    @Before
    public void init1(){
        Product product = new Product();
        product.setId(2);
        product.setName("Kifli");
        product.setPrice(500);
        product.setProductType(ProductType.FOOD);
        product.setDescription("A kifli jo.");
        product.setPopularity(80);
        when(productRepository.getById(2)).thenReturn(product);
    }

    @Test
    public void getAllProducts()
    {
        List<ProductDTO> found = productService.getAllProducts();
        assertThat(found.get(0).getDescription()).isEqualTo("Kenyer");
        assertThat(found.get(1).getDescription()).isEqualTo("Kifli");

    }


}
