package com.example.onlineshop.dtos;

import com.example.onlineshop.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartDTO {
    private Integer id;
    private List<Product> products;
    private Integer sumPrice;
}
