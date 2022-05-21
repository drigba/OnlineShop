package com.example.onlineshop.repository;

import com.example.onlineshop.entity.Product;
import com.example.onlineshop.enums.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    Product findByNameAndPriceAndDescription(String name, Integer price, String description);
    List<Product> findByProductType(ProductType productType);

    List<Product> findByNameStartingWith(String s);
}
