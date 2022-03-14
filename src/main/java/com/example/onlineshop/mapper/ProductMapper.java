package com.example.onlineshop.mapper;

import com.example.onlineshop.dtos.ProductDTO;
import com.example.onlineshop.entity.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    Product dtoToEntity(ProductDTO productDTO);
}
