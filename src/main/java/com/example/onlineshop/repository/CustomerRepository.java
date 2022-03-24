package com.example.onlineshop.repository;

import com.example.onlineshop.entity.Customer;
import com.example.onlineshop.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
}
