package com.example.onlineshop.controller;

import com.example.onlineshop.dtos.CustomerDTO;
import com.example.onlineshop.dtos.ProductDTO;
import com.example.onlineshop.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @PostMapping(path="/create")
    public CustomerDTO createCustomer(@RequestBody CustomerDTO customerDTO){ return customerService.createCustomer(customerDTO); }

    @GetMapping(path="/getall")
    public List<CustomerDTO> getAllCustomers() { return customerService.getAllCustomers(); }

    @PostMapping(path="/update")
    public CustomerDTO updateCustomer(@RequestBody CustomerDTO customerDTO, @RequestParam Integer id) { return customerService.updateCustomer(customerDTO, id); }

    @PostMapping(path="/delete")
    public ResponseEntity<Void> deleteCustomer(@RequestParam Integer id)
    {
        try{
            customerService.deleteCustomer(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        catch(Exception e)
        {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @GetMapping(path="/get")
    public CustomerDTO getCustomer(@RequestParam Integer id ){
        return customerService.getCustomer(id);
    }
}
