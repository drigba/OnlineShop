package com.example.onlineshop.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="customers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Customer extends Person {
    private String address;
    @OneToMany
    //TODO JoinColumn
    private List<Product> cart;
    @OneToMany
    //TODO JoinColumn
    private List<Product> favourites;
}
