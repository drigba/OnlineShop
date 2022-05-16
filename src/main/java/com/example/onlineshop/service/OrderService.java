package com.example.onlineshop.service;

import com.example.onlineshop.dtos.OrderDTO;
import com.example.onlineshop.entity.Order;
import com.example.onlineshop.enums.OrderStatus;
import com.example.onlineshop.mapper.OrderMapper;
import com.example.onlineshop.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderMapper orderMapper;

    private Order dtoToOrder(OrderDTO orderDTO){
        return orderMapper.dtoToEntity(orderDTO);
    }

    private OrderDTO orderToDTO(Order order){
        return orderMapper.entityToDTO(order);
    }


    public OrderDTO createOrder(OrderDTO orderDTO){
        Order order = dtoToOrder(orderDTO);
        orderRepository.save(order);
        return orderToDTO(order);
    }

    public OrderDTO updateOrder(OrderDTO orderDTO, Integer id) {
        orderRepository.getById(id).setFromAddress(orderDTO.getFromAddress());
        orderRepository.getById(id).setPrice(orderDTO.getPrice());
        orderRepository.getById(id).setAddress(orderDTO.getAddress());
        orderRepository.getById(id).setProductList(orderDTO.getProductList());
        orderRepository.getById(id).setOrderStatus(orderDTO.getOrderStatus());
        return orderToDTO(orderRepository.getById(id));
    }

    public void deleteOrder(Integer id) {
        orderRepository.deleteById(id);
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
}
