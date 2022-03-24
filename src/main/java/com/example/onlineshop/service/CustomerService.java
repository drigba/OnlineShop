package com.example.onlineshop.service;

import com.example.onlineshop.dtos.CustomerDTO;
import com.example.onlineshop.entity.Customer;
import com.example.onlineshop.mapper.CustomerMapper;
import com.example.onlineshop.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerMapper customerMapper;

    private Customer dtoToCustomer(CustomerDTO customerDTO)
    {
        return customerMapper.dtoToEntity(customerDTO);
    }

    private CustomerDTO customerToDTO(Customer customer)
    {
        return CustomerDTO.builder()
                .name(customer.getName())
                .email(customer.getEmail())
                .cart(customer.getCart())
                .favourites(customer.getFavourites())
                .address(customer.getAddress())
                .build();
    }
}
