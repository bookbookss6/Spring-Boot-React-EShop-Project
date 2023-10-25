package com.eshopbackend.entity;

import com.eshopbackend.payload.Category;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Blob;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @Enumerated(EnumType.STRING)
    private Category category;
    private int price;

    @Lob
    private Blob image;

    @OneToOne(mappedBy = "product" , cascade = CascadeType.ALL , orphanRemoval = true)
    private CartView cartView;

    @OneToOne(mappedBy = "product" , cascade = CascadeType.ALL , orphanRemoval = true)
    private OrderList orderList;

}
