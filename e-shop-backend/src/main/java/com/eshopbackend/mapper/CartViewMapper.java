package com.eshopbackend.mapper;

import com.eshopbackend.entity.CartView;
import com.eshopbackend.payload.CartViewDto;
import com.eshopbackend.payload.ProductDto;

import java.sql.SQLException;

public class CartViewMapper {

    public static CartViewDto mapToDto(CartView cartView) throws SQLException {

        CartViewDto cartViewDto = new CartViewDto();
        ProductDto productDto = new ProductDto();

        productDto.setId(cartView.getProduct().getId());
        productDto.setPrice(cartView.getProduct().getPrice());
        productDto.setTitle(cartView.getProduct().getTitle());
        productDto.setCategory(cartView.getProduct().getCategory());
        productDto.setImage(cartView.getProduct().getImage().getBytes(1,(int)cartView.getProduct().getImage().length()));

        cartViewDto.setId(cartView.getId());
        cartViewDto.setUser(UserMapper.mapToDto(cartView.getUser()));
        cartViewDto.setProductCount(cartView.getProductCount());
        cartViewDto.setProduct(productDto);

        return cartViewDto;
    }

    public static CartView mapToEntity(CartViewDto cartViewDto) throws SQLException {
        CartView cartView = new CartView();
        ProductDto productDto = new ProductDto();

        productDto.setId(cartViewDto.getProduct().getId());
        productDto.setPrice(cartViewDto.getProduct().getPrice());
        productDto.setTitle(cartViewDto.getProduct().getTitle());
        productDto.setCategory(cartView.getProduct().getCategory());
        productDto.setImage(cartViewDto.getProduct().getImage());

        cartView.setId(cartViewDto.getId());
        cartView.setUser(UserMapper.mapToEntity(cartViewDto.getUser()));
        cartView.setProductCount(cartViewDto.getProductCount());
        cartView.setProduct(ProductMapper.mapToEntity(productDto));

        return cartView;
    }
}
