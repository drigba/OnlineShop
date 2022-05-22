package com.example.onlineshop.dtos;

import com.example.onlineshop.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class CartDTO implements Serializable {

    private List<Product> products;
    private Integer sumPrice;
    private Integer id;
    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
