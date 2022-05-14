package com.example.onlineshop.service;

import com.example.onlineshop.dtos.CustomerDTO;
import com.example.onlineshop.entity.Cart;
import com.example.onlineshop.entity.Customer;
import com.example.onlineshop.entity.Person;
import com.example.onlineshop.mapper.CustomerMapper;
import com.example.onlineshop.repository.CartRepository;
import com.example.onlineshop.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerMapper customerMapper;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private EmailService emailService;

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

    @Scheduled(cron = "0 0 3 1 * ?")
    @Transactional(value = "")
    protected void autoCartEmpty(){
        customerRepository.findAll().stream()
                .filter(customer -> !customer.getCart().getProducts().isEmpty())
                .forEach(customer -> {
                    Cart cart = customer.getCart();
                    cart.getProducts().forEach(product -> customer.getFavourites().add(product));
                    cart.getProducts().clear();
                    cart.setSumPrice(0);
                    cartRepository.save(cart);
                });
    }

    @Scheduled(cron = "0 0 0 * * ?")
    private void cartIsNotEmpty(){
        String[] emails = customerRepository.findAll().stream()
                .filter(customer -> !customer.getCart().getProducts().isEmpty())
                .map(Person::getEmail)
                .toArray(String[]::new);
        String subject = "Ne hagyja arvalkodni termekeit a kosaraban!";
        String shopName = "Anonymus";
        emailService.sendMessage(emails, subject, shopName);
    }
}
