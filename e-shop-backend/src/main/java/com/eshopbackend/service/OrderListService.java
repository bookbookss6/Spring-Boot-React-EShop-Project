package com.eshopbackend.service;
import com.eshopbackend.payload.CartViewDto;
import com.eshopbackend.payload.OrderListDto;

import java.sql.SQLException;
import java.util.List;

public interface OrderListService {

    void save(Long productId,Long productCount,String username) throws SQLException;
    void saveAll(CartViewDto[] cartViewDtos);
    List<OrderListDto> getOrderLists(String username);
}
