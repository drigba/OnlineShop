package com.example.onlineshop.service;

import com.example.onlineshop.dtos.OrderDTO;
import com.example.onlineshop.entity.Order;
import com.example.onlineshop.mapper.OrderMapper;
import com.example.onlineshop.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
