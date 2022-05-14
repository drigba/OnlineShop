package com.example.onlineshop.service;

import com.example.onlineshop.TestEnvContainer;
import com.example.onlineshop.dtos.OrderDTO;
import com.example.onlineshop.entity.Product;
import com.example.onlineshop.enums.OrderStatus;
import com.example.onlineshop.enums.ProductType;
import com.example.onlineshop.mapper.OrderMapper;
import com.example.onlineshop.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@WebMvcTest(OrderService.class)
@ContextConfiguration(classes = TestEnvContainer.class)
class OrderServiceTest {
    private OrderService orderService = new OrderService();
    /*
    @Autowired
    private MockMvc mvc;

    @MockBean
    private OrderService orderService;
    */


    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderMapper orderMapper;

    private OrderDTO orderDTO;

    @BeforeEach
    void setUp() {
        List<Product> _prod = new ArrayList<>();
        _prod.add(new Product(1,"termek1",200, ProductType.ELECTRONICS,"t1_description",20));
        _prod.add(new Product(2,"termek2",400,ProductType.FOOD,"t2_description",30));


        orderDTO = new OrderDTO();
        orderDTO.setId(1);
        orderDTO.setFromAddress("21_Jumpstreet");
        orderDTO.setAddress("Backstreet");
        orderDTO.setProductList(_prod);
        orderDTO.setPrice(600);
        orderDTO.setOrderStatus(OrderStatus.DELIVERED);
    }

    @Test
    void createOrder_test() {
        assertThat(orderService.createOrder(orderDTO)).isEqualTo(orderDTO);
    }

    @Test
    void updateOrder_test() {
    }

    @Test
    void deleteOrder_test() {
    }

    @Test
    void getAllOrders_test() {
    }

    @Test
    void getSomeOrders_test() {
    }

    @Test
    void getOrdersByStatus_test() {
    }

    @Test
    void getOrdersByAddress_test() {
    }
}