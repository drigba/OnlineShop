package com.example.onlineshop.mapper;

import com.example.onlineshop.dtos.CustomerDTO;
import com.example.onlineshop.entity.Customer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    Customer dtoToEntity(CustomerDTO customerDTO);
}
