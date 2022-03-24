package com.example.onlineshop.mapper;

import com.example.onlineshop.dtos.CustomerDTO;
import com.example.onlineshop.dtos.ProductDTO;
import com.example.onlineshop.entity.Customer;
import com.example.onlineshop.entity.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    Customer dtoToEntity(CustomerDTO customerDTO);
}
