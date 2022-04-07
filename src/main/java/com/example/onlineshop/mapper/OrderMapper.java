package com.example.onlineshop.mapper;

import com.example.onlineshop.dtos.OrderDTO;
import com.example.onlineshop.entity.Order;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    Order dtoToEntity(OrderDTO orderDTO);
    OrderDTO entityToDTO(Order order);
}
