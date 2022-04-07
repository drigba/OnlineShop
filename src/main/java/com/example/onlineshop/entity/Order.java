package com.example.onlineshop.entity;

import com.example.onlineshop.enums.OrderStatus;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="orders")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String fromAddress;
    private String Address;
    @ManyToMany
    // TODO @JoinTable
    private List<Product> productList;
    private Integer price;
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;
}
