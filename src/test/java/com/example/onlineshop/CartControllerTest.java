package com.example.onlineshop;
import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.onlineshop.controller.CartController;
import com.example.onlineshop.repository.CartRepository;
import com.example.onlineshop.service.CartService;
import com.example.onlineshop.service.ProductService;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.assertj.core.api.Assertions.assertThat;

//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@WebMvcTest(CartController.class)
@Import(CartController.class)

public class CartControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CartService cartService;

    @MockBean
    private CartRepository cartRepository;

    @Test
    public void getHello() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/cart").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
                //.andExpect(content().string(equalTo("Greeting from Spring boot")));
    }

    /*
    @Autowired
    private TestRestTemplate template;

    @Test
    public void getHello_2(){
        ResponseEntity<String> response = template.getForEntity("/cart", String.class);
        assertThat(response.getBody()).isEqualTo("Greeting from Spring boot");
    }

     */
}
