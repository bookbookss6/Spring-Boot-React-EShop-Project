package com.eshopbackend.payload;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {

    private Long id;
    private String title;
    private Category category;
    private int price;
    private byte[] image;
}
