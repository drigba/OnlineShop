package com.example.onlineshop;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.example.onlineshop.controller.CartController;
import com.example.onlineshop.dtos.CartDTO;
import com.example.onlineshop.dtos.ProductDTO;
import com.example.onlineshop.entity.Cart;
import com.example.onlineshop.entity.Product;
import com.example.onlineshop.enums.ProductType;
import com.example.onlineshop.mapper.ProductMapper;
import com.example.onlineshop.repository.CartRepository;
import com.example.onlineshop.service.CartService;
import com.example.onlineshop.service.ProductService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
//@WebMvcTest(CartController.class)
@ContextConfiguration(classes = TestEnvContainer.class, initializers = ApplicationInitializer.class)
@Import(CartController.class)


public class CartControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private CartService cartService;

    @MockBean
    private CartRepository cartRepository;

    @Autowired
    private ProductMapper productMapper;

    private CartDTO cartDTO;
    private Cart cart;

    @BeforeEach
    void setUp() {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setName("TV");
        productDTO.setPrice(23589115);
        productDTO.setProductType(ProductType.ELECTRONICS);
        List<Product> _prod = new ArrayList<>();
        _prod.add(new Product(1,"termek1",200,ProductType.ELECTRONICS,"t1_description",20));
        _prod.add(new Product(2,"termek2",400,ProductType.FOOD,"t2_description",30));

        System.out.println("_prod product size:  " + _prod.size());
        cartDTO = new CartDTO();
        cartDTO.setProducts(_prod);
        System.out.println("cartDTO product size:  " + cartDTO.getProducts().size());
        cartDTO.setSumPrice(600);

        cart = Cart.builder().id(1).products(_prod).sumPrice(600).build();

        when(cartRepository.getById(1)).thenReturn(cart);

        cartService.addProductToCart(productDTO,1);


    }

    /*
    @Test
    @WithMockUser(username = "username", roles={"Admin"})
    @RequestParam(CartDTO)
    public void getHello() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/getcontent").param("cartDTO", String.valueOf(cartDTO))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
                //.andExpect(content().string(equalTo("Greeting from Spring boot")));
    }
    */


    @Autowired
    private RestTemplate template;

    @Autowired
    private ServerProperties serverProperties;

    @Test
    public void getHello_2(){
        int port = serverProperties.getPort();
        System.out.println("___server port" + port);


        /*
        String scheme = request.getScheme();
        String userInfo = request.getRemoteUser();
        String host = request.getLocalAddr();
        int port = request.getLocalPort();
        String path = "/app2/myservice";
        String query = "par=1";
        URI uri = new URI(scheme, userInfo, host, port, path, query, null);

         */

        CartDTO response = template.getForObject("/cart/getcontent", CartDTO.class);
        assertEquals(response.getProducts().get(0).getName(),"termek1");
    }


}
