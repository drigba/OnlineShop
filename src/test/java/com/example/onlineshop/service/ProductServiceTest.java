package com.example.onlineshop.service;

import com.example.onlineshop.TestEnvContainer;
import com.example.onlineshop.dtos.ProductDTO;
import com.example.onlineshop.entity.Product;
import com.example.onlineshop.enums.ProductType;
import com.example.onlineshop.repository.CartRepository;
import com.example.onlineshop.repository.OrderRepository;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = TestEnvContainer.class)
public class ProductServiceTest {

    @MockBean
    private ProductRepository productRepository;

    @MockBean
    private OrderRepository orderRepository;

    @MockBean
    private CartRepository cartRepository;

    @Autowired
    private ProductService productService;

    List<ProductDTO> _list = new ArrayList<>();
    List<Product> _prodlist = new ArrayList<>();
    Product product = new Product();
    ProductDTO productDTO = new ProductDTO();
    Product product2 = new Product();
    ProductDTO productDTO2 = new ProductDTO();

    @BeforeEach
    public void init(){
        //product.setId(1);
        product.setName("Kenyer");
        product.setPrice(1000);
        product.setProductType(ProductType.FOOD);
        product.setDescription("A kenyer jo.");
        product.setPopularity(64);

        _prodlist.add(product);

        productDTO.setName("Kenyer");
        productDTO.setPrice(1000);
        productDTO.setProductType(ProductType.FOOD);
        productDTO.setDescription("A kenyer jo.");
        productDTO.setPopularity(64);

        _list.add(productDTO);


        //product2.setId(2);
        product2.setName("Kifli");
        product2.setPrice(300);
        product2.setProductType(ProductType.FOOD);
        product2.setDescription("Sajtos kifli");
        product2.setPopularity(80);

        _prodlist.add(product2);


        productDTO2.setName("Kifli");
        productDTO2.setPrice(300);
        productDTO2.setProductType(ProductType.FOOD);
        productDTO2.setDescription("Sajtos kifli");
        productDTO2.setPopularity(80);

        _list.add(productDTO2);

        when(productRepository.getById(1)).thenReturn(product);
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
    public void getAllProducts()
    {
        when(this.productRepository.findAll()).thenReturn(_prodlist);

        assertThat(this.productService.getAllProducts().get(0).getDescription()).isEqualTo("A kenyer jo.");
        assertThat(this.productService.getAllProducts().get(1).getDescription()).isEqualTo("Sajtos kifli");
        assertThat(this.productService.getAllProducts()).isEqualTo(_list);
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

    @Test
    void getSomeProducts() {
        assertThat(this.productService.getSomeProducts(_prodlist)).isEqualTo(_list);
    }

    @Test
    void getProductsByType() {
        when(this.productRepository.findByProductType(ProductType.FOOD)).thenReturn(_prodlist);
        assertThat(this.productService.getProductsByType(ProductType.FOOD)).isEqualTo(_list);
    }

    @Test
    void sortProductsByPrice() {
        when(this.productRepository.findAll()).thenReturn(_prodlist);

        assertThat(this.productService.sortProductsByPrice(_list,true)).isEqualTo(_list);
        assertThat(this.productService.getAllProducts().get(0).getDescription()).isEqualTo("A kenyer jo.");
        assertThat(this.productService.getAllProducts().get(1).getDescription()).isEqualTo("Sajtos kifli");
    }

    @Test
    void sortProductsByPrice_reversed(){
        List<ProductDTO> _reversed = new ArrayList<>();
        _reversed.add(productDTO2);
        _reversed.add(productDTO);

        List<Product> _prodlist_reversed = new ArrayList<>();
        _prodlist_reversed.add(product2);
        _prodlist_reversed.add(product);


        assertThat(this.productService.sortProductsByPrice(_list,false)).isEqualTo(_reversed);

        when(this.productRepository.findAll()).thenReturn(_prodlist_reversed);
        assertThat(this.productService.getAllProducts().get(1).getDescription()).isEqualTo("A kenyer jo.");
        assertThat(this.productService.getAllProducts().get(0).getDescription()).isEqualTo("Sajtos kifli");



    }




}
