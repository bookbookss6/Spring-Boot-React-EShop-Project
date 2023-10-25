package com.eshopbackend.entity;

import com.eshopbackend.payload.Role;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "user" , cascade = CascadeType.ALL ,orphanRemoval = true)
    private Set<CartView> cartViews;

    @OneToMany(mappedBy = "user" , cascade = CascadeType.ALL ,orphanRemoval = true)
    private Set<OrderList> orderLists;

}
