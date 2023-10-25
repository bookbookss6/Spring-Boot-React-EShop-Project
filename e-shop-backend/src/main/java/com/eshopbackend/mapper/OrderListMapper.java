package com.eshopbackend.mapper;

import com.eshopbackend.entity.OrderList;
import com.eshopbackend.payload.OrderListDto;

import java.sql.SQLException;

public class OrderListMapper {

    public static OrderListDto mapToDto(OrderList orderList) throws SQLException {
        OrderListDto orderListDto = new OrderListDto();
        orderListDto.setId(orderList.getId());
        orderListDto.setProduct(ProductMapper.mapToDto(orderList.getProduct()));
        orderListDto.setUser(UserMapper.mapToDto(orderList.getUser()));
        orderListDto.setProductCount(orderList.getProductCount());

        return orderListDto;
    }

    public static OrderList mapToEntity(OrderListDto orderListDto) throws SQLException {
        OrderList orderList = new OrderList();
        orderList.setId(orderListDto.getId());
        orderList.setProduct(ProductMapper.mapToEntity(orderListDto.getProduct()));
        orderList.setUser(UserMapper.mapToEntity(orderListDto.getUser()));
        orderList.setProductCount(orderListDto.getProductCount());

        return orderList;
    }
}
