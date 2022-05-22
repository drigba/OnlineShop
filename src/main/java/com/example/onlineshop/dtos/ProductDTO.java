package com.example.onlineshop.dtos;

import com.example.onlineshop.enums.ProductType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class ProductDTO {
    private Integer id;
    private String name;
    private Integer price;
    private ProductType productType;
    private String description;
    private Integer popularity;
}
