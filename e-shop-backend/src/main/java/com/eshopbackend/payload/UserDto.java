package com.eshopbackend.payload;

import com.eshopbackend.entity.CartView;
import com.eshopbackend.entity.OrderList;
import lombok.*;

import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private Long id;
    private String username;
    private String password;
    private Role role;
}
