package com.eshopbackend.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderListDto {
    private Long id;
    private Long productCount;
    private ProductDto product;
    private UserDto user;
}
