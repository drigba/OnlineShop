package com.example.onlineshop.controller;

import com.example.onlineshop.dtos.CartDTO;
import com.example.onlineshop.dtos.ProductDTO;
import com.example.onlineshop.entity.Product;
import com.example.onlineshop.enums.ProductType;
import com.example.onlineshop.repository.RetailerRepository;
import com.example.onlineshop.service.CartService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.sun.source.tree.Tree;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.web.JsonPath;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.*;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CartController.class)
@AutoConfigureMockMvc(addFilters = false)
class CartControllerTest {

    @Autowired
    private CartController controller;

    @MockBean
    private CartService cartService;

    @MockBean
    private RetailerRepository retailerRepository;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    ProductDTO productDTO = new ProductDTO();
    Map<ProductDTO, Long> _map = new TreeMap<>();
    CartDTO cartDTO = new CartDTO();

    Gson gson = new Gson();


    @BeforeEach
    void setUp() {
        productDTO.setName("termek1");
        productDTO.setPrice(200);
        productDTO.setProductType(ProductType.ELECTRONICS);

        productDTO.setName("termek2");
        productDTO.setPrice(400);
        productDTO.setProductType(ProductType.FOOD);


        _map.put(productDTO,Long.valueOf(1));
        //_map.put(productDTO,Long.valueOf(1));

        List<Product> _prod = new ArrayList<>();
        _prod.add(new Product(1,"termek1",200,ProductType.ELECTRONICS,"t1_description",20));
        //_prod.add(new Product(2,"termek2",400,ProductType.FOOD,"t2_description",30));

        cartDTO.setProducts(_prod);
        cartDTO.setSumPrice(200);
    }

    @Test
    void addProductToCart() throws  Exception{
        when(cartService.addProductToCart(productDTO,cartDTO)).thenReturn(cartDTO);
        mockMvc.perform(post("/cart/addproduct").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(cartDTO)))
                .andExpect(status().isCreated());
    }

    @Test
    void deleteProductFromCart() {
    }

    @Test
    void getCartContent() throws Exception {
        String json = gson.toJson(cartDTO);
        given(this.cartService.getCartContent(cartDTO)).willReturn(_map);
        //when(cartService.getCartContent(cartDTO)).thenReturn(_map);
        mockMvc.perform(get("/getcontent")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk());
    }

    @Test
    void clearCart() {
    }

    @Test
    void testtest() throws Exception{

        given(this.cartService.addProductToCart(productDTO,cartDTO)).willReturn(cartDTO);
        String json1 = gson.toJson(productDTO);
        String json2 = gson.toJson(cartDTO);
        this.mockMvc.perform(post("/addproduct")
                        //.sess
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString((cartDTO))))
                .andExpect(status().isCreated());
    }
}