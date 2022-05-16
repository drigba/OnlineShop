package com.example.onlineshop.mapper;

import com.example.onlineshop.dtos.RetailerDTO;
import com.example.onlineshop.entity.Retailer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RetailerMapper {
    Retailer dtoToEntity(RetailerDTO retailerDTO);
}
