package com.example.onlineshop.service;

import com.example.onlineshop.dtos.OrderDTO;
import com.example.onlineshop.entity.Customer;
import com.example.onlineshop.entity.Order;
import com.example.onlineshop.entity.Product;
import com.example.onlineshop.enums.OrderStatus;
import com.example.onlineshop.mapper.OrderMapper;
import com.example.onlineshop.repository.CustomerRepository;
import com.example.onlineshop.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class OrderService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderMapper orderMapper;

    private Order dtoToOrder(OrderDTO orderDTO){
        return Order.builder()
                .fromAddress(orderDTO.getFromAddress())
                .address(orderDTO.getAddress())
                .productList(orderDTO.getProductList())
                .price(orderDTO.getPrice())
                .orderStatus(orderDTO.getOrderStatus())
                .build();
    }

    private OrderDTO orderToDTO(Order order){
        return OrderDTO.builder()
                .id(order.getId())
                .fromAddress(order.getFromAddress())
                .address(order.getAddress())
                .productList(order.getProductList())
                .price(order.getPrice())
                .orderStatus(order.getOrderStatus())
                .build();
    }

    public OrderDTO createOrder(Integer customerId){
        Customer customer = customerRepository.getById(customerId);
        List<Product> productList= customerRepository.getById(customerId).getCart().getProducts();
        Integer sumPrice = 0;
        for (Product product : productList) {
            sumPrice += product.getPrice();
        }
        Order order = new Order();
        order.setProductList(new ArrayList<>(productList));
        order.setPrice(sumPrice);
        order.setAddress(customer.getAddress());
        order.setOrderStatus(OrderStatus.ORDERED);
        orderRepository.save(order);
        customer.getOrders().add(order);
        customerRepository.save(customer);
        log.info("Order created for customer: " + customerId);
        return orderToDTO(order);
    }

    public OrderDTO updateOrder(OrderDTO orderDTO, Integer id) {
        Order tobesaved = orderRepository.getById(id);
        tobesaved.setFromAddress(orderDTO.getFromAddress());
        tobesaved.setPrice(orderDTO.getPrice());
        tobesaved.setAddress(orderDTO.getAddress());
        tobesaved.setProductList(orderDTO.getProductList());
        tobesaved.setOrderStatus(orderDTO.getOrderStatus());
        orderRepository.save(tobesaved);
        log.info("Order updated: " + id);
        return orderToDTO(orderRepository.getById(id));
    }

    public void deleteOrder(Integer id) {
        orderRepository.deleteById(id);
        log.info("Order deleted: " + id);
    }

    public List<OrderDTO> getAllOrders() {
        return orderRepository.findAll()
                .stream()
                .map(this::orderToDTO)
                .collect(Collectors.toList());
    }

    public List<OrderDTO> getSomeOrders(List<Order> sold) {
        return sold
                .stream()
                .map(this::orderToDTO)
                .collect(Collectors.toList());
    }

    public List<OrderDTO> getOrdersByStatus(OrderStatus orderStatus) {
        return orderRepository.findByOrderStatus(orderStatus)
                .stream()
                .map(this::orderToDTO)
                .collect(Collectors.toList());
    }

    public List<OrderDTO> getOrdersByAddress(String address) {
        return orderRepository.findByAddress(address)
                .stream()
                .map(this::orderToDTO)
                .collect(Collectors.toList());
    }

    public OrderDTO getOrder(Integer id) {
        return orderToDTO(orderRepository.getById(id));
    }
}
