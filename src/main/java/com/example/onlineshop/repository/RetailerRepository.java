package com.example.onlineshop.repository;

import com.example.onlineshop.entity.Retailer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RetailerRepository extends JpaRepository<Retailer, Integer> {
}
