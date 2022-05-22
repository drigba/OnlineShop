package com.example.onlineshop.dtos;

import com.example.onlineshop.entity.Cart;
import com.example.onlineshop.entity.Order;
import com.example.onlineshop.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class CustomerDTO {
    private String name;
    private String email;
    private String address;
    private Cart cart;
    private List<Product> favourites;
    private List<Order> orders;
}
