package com.example.onlineshop.service;

import com.example.onlineshop.dtos.CustomerDTO;
import com.example.onlineshop.dtos.ProductDTO;
import com.example.onlineshop.entity.Cart;
import com.example.onlineshop.entity.Customer;
import com.example.onlineshop.entity.Person;
import com.example.onlineshop.mapper.CustomerMapper;
import com.example.onlineshop.repository.CartRepository;
import com.example.onlineshop.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
                .orders(customer.getOrders())
                .name(customer.getName())
                .email(customer.getEmail())
                .cart(customer.getCart())
                .favourites(customer.getFavourites())
                .address(customer.getAddress())
                .build();
    }

    public CustomerDTO createCustomer(CustomerDTO customerDTO){
        Customer customer = dtoToCustomer(customerDTO);
        customerRepository.save(customer);
        return customerToDTO(customer);
    }

    public List<CustomerDTO> getAllCustomers() {
        return customerRepository.findAll()
                .stream()
                .map(this::customerToDTO)
                .collect(Collectors.toList());
    }

    public CustomerDTO updateCustomer(CustomerDTO customerDTO, Integer id) {
        Customer customer = customerRepository.getById(id);
        customer.setName(customerDTO.getName());
        customer.setAddress(customerDTO.getAddress());
        customer.setEmail(customerDTO.getEmail());

        customerRepository.save(customer);

        return customerToDTO(customerRepository.getById(id));
    }

    public void deleteCustomer(Integer id) {
        customerRepository.deleteById(id);
    }

    @Scheduled(cron = "0 0 3 1 * ?")
    @Transactional(value = "1")
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

    public CustomerDTO getCustomer(Integer id) {
        return customerToDTO(customerRepository.getById(id));
    }
}
