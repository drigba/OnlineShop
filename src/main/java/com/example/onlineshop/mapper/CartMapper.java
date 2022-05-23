package com.example.onlineshop.mapper;

import com.example.onlineshop.dtos.CartDTO;
import com.example.onlineshop.entity.Cart;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring")
public interface CartMapper {
    Cart dtoToEntity(CartDTO cartDTO);
}
