package com.example.onlineshop.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="retailers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Retailer extends Person {
    private String address;

    @ManyToMany
    @JoinTable(
            name= "retailers_products",
            joinColumns = @JoinColumn(name = "retailer_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<Product> products;

    @ManyToMany
    @JoinTable(
            name= "retailers_sold",
            joinColumns = @JoinColumn(name = "retailer_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<Product> sold;
}
