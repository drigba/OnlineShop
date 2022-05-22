package com.example.onlineshop.controller;

import com.example.onlineshop.TestEnvContainer;
import com.example.onlineshop.dtos.OrderDTO;
import com.example.onlineshop.dtos.ProductDTO;
import com.example.onlineshop.entity.Product;
import com.example.onlineshop.enums.OrderStatus;
import com.example.onlineshop.enums.ProductType;
import com.example.onlineshop.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class OrderControllerTest {

    @BeforeEach
    void setUp() {

    }

    @Test
    void createOrder() {


    }

    @Test
    void getAllOrders() {
    }

    @Test
    void updateOrder() {
    }

    @Test
    void deleteOrder() {
    }

    @Test
    void getOrdersByStatus() {
    }

    @Test
    void getOrdersByAddress() {
    }
}