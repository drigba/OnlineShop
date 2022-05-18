package com.example.onlineshop.dtos;

import com.example.onlineshop.entity.Product;
import com.example.onlineshop.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDTO {
    private Integer id;
    private String fromAddress;
    private String address;
    private List<Product> productList;
    private Integer price;
    private OrderStatus orderStatus;
}
