package com.example.onlineshop.entity;

import lombok.*;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name="carts")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToMany
    // TODO @JoinTable
    private List<Product> products;
    private Integer sumPrice;
}
