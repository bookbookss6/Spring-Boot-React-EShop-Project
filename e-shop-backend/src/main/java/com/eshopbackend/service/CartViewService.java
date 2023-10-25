package com.eshopbackend.service;

import com.eshopbackend.payload.CartViewDto;

import java.sql.SQLException;
import java.util.List;

public interface CartViewService {
    void save(Long productId,String username) throws SQLException;
    void delete(Long productId,String username);
    void deleteAll(String username);
    List<CartViewDto> getCartViews(String username);
    void setProductCount(Long productCount,Long productId,String username);
}
