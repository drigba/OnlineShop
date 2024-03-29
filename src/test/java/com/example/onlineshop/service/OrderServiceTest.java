package com.example.onlineshop.service;

import com.example.onlineshop.TestEnvContainer;
import com.example.onlineshop.dtos.OrderDTO;
import com.example.onlineshop.entity.Cart;
import com.example.onlineshop.entity.Customer;
import com.example.onlineshop.entity.Order;
import com.example.onlineshop.entity.Product;
import com.example.onlineshop.enums.OrderStatus;
import com.example.onlineshop.enums.ProductType;
import com.example.onlineshop.repository.CartRepository;
import com.example.onlineshop.repository.CustomerRepository;
import com.example.onlineshop.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import org.springframework.boot.test.context.SpringBootTest;

@RunWith(SpringRunner.class)
//@WebMvcTest(OrderService.class)
@SpringBootTest
@ContextConfiguration(classes = TestEnvContainer.class)
class OrderServiceTest {
    // private OrderService orderService = new OrderService();

    // @Autowired
    // private MockMvc mvc;

    @Autowired
    private OrderService orderService;

    @MockBean
    private OrderRepository orderRepository;

    @MockBean
    private CartRepository cartRepository;

    @MockBean
    private CustomerRepository customerRepository;
/*
    @Autowired
    private OrderMapper orderMapper;

 */

    private OrderDTO orderDTO;
    private Order order;
    List<Product> _prod = new ArrayList<>();
    List<OrderDTO> _list = new ArrayList<>();
    List<Order> _orders = new ArrayList<>();

    @BeforeEach
    void setUp() {

        _prod.add(new Product(1,"termek1",200, ProductType.ELECTRONICS,"t1_description",20));
        _prod.add(new Product(2,"termek2",400,ProductType.FOOD,"t2_description",30));


        orderDTO = new OrderDTO();
        orderDTO.setId(1);
        orderDTO.setFromAddress("21_Jumpstreet");
        orderDTO.setAddress("Backstreet");
        orderDTO.setProductList(_prod);
        orderDTO.setPrice(600);
        orderDTO.setOrderStatus(OrderStatus.DELIVERED);

        //when(orderRepository.findAll()).then();
        order = new Order();
        order.setId(1);
        order.setFromAddress("21_Jumpstreet");
        order.setAddress("Backstreet");
        order.setProductList(_prod);
        order.setPrice(600);
        order.setOrderStatus(OrderStatus.DELIVERED);

        Cart cart = Cart.builder().id(1).products(_prod).sumPrice(600).build();

        Customer customer = Customer.builder()
                .id(1)
                .name("Pista")
                .email("pista@email.hu")
                .password("password")
                .address("valahol")
                .cart(cart)
                .favourites(new ArrayList<>())
                .orders(new ArrayList<>())
                .build();

        _list.add(orderDTO);
        _orders.add(order);

        when(customerRepository.getById(1)).thenReturn(customer);
        //when(customer.getCart()).thenReturn(cart);
        //when(cart.getProducts()).thenReturn(_prod);
        //when(cart.getSumPrice()).thenReturn(cart.getSumPrice());

        //_list.add(orderDTO);
        //_orders.add(order);
    }

    @Test
    void createOrder_test() {
        //when(this.orderRepository.save(order)).thenReturn(order);

        assertThat(orderService.createOrder(1).getPrice()).isEqualTo(customerRepository.getById(1).getCart().getSumPrice());
    }

    @Test
    void updateOrder_test() {
        this.orderService.createOrder(1);

        OrderDTO orderDTO2 = new OrderDTO();
        orderDTO.setId(1);
        orderDTO2.setFromAddress("21_Jumpstreet");
        orderDTO2.setAddress("Backstreet");
        orderDTO2.setProductList(_prod);
        orderDTO2.setPrice(600);
        orderDTO2.setOrderStatus(OrderStatus.DELIVERED);

        when(this.orderRepository.getById(1)).thenReturn(order);

        assertThat(orderService.updateOrder(orderDTO2,1)).isEqualTo(orderDTO);
    }

    @Test
    void deleteOrder_test() {

    }

    @Test
    void getAllOrders_test() {
        when(this.orderRepository.findAll()).thenReturn(_orders);
        assertThat(orderService.getAllOrders()).isEqualTo(_list);
    }

    @Test
    void getSomeOrders_test() {
        assertThat(orderService.getSomeOrders(_orders)).isEqualTo(_list);
    }

    @Test
    void getOrdersByStatus_test() {
        when(this.orderRepository.findByOrderStatus(OrderStatus.DELIVERED)).thenReturn(_orders);
        assertThat(orderService.getOrdersByStatus(OrderStatus.DELIVERED)).isEqualTo(_list);
    }

    @Test
    void getOrdersByAddress_test() {
        when(this.orderRepository.findByAddress("21_Jumpstreet")).thenReturn(_orders);
        assertThat(orderService.getOrdersByAddress("21_Jumpstreet")).isEqualTo(_list);
    }
}
