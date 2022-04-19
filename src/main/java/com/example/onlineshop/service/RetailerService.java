package com.example.onlineshop.service;

import com.example.onlineshop.dtos.RetailerDTO;
import com.example.onlineshop.entity.Retailer;
import com.example.onlineshop.mapper.CustomerMapper;
import com.example.onlineshop.mapper.RetailerMapper;
import com.example.onlineshop.repository.CustomerRepository;
import com.example.onlineshop.repository.RetailerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RetailerService {

    @Autowired
    private RetailerRepository retailerRepository;

    @Autowired
    private RetailerMapper retailerMapper;

    private Retailer dtoToRetailer(RetailerDTO retailerDTO){ return  retailerMapper.dtoToEntity(retailerDTO);}

    private RetailerDTO retailerToDTO(Retailer retailer){
        return  RetailerDTO.builder()
                .name(retailer.getName())
                .email(retailer.getEmail())
                .products(retailer.getProducts())
                .sold(retailer.getSold())
                .build();

    }




}
