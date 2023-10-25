package com.eshopbackend.service.impl;

import com.eshopbackend.entity.OrderList;
import com.eshopbackend.entity.Product;
import com.eshopbackend.entity.User;
import com.eshopbackend.exception.ResourceNotFoundException;
import com.eshopbackend.mapper.OrderListMapper;
import com.eshopbackend.mapper.ProductMapper;
import com.eshopbackend.mapper.UserMapper;
import com.eshopbackend.payload.CartViewDto;
import com.eshopbackend.payload.OrderListDto;
import com.eshopbackend.payload.ProductDto;
import com.eshopbackend.payload.UserDto;
import com.eshopbackend.repository.OrderListRepository;
import com.eshopbackend.repository.ProductRepository;
import com.eshopbackend.repository.UserRepository;
import com.eshopbackend.service.OrderListService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrderListServiceImpl implements OrderListService {

    private OrderListRepository orderListRepository;
    private ProductRepository productRepository;
    private UserRepository userRepository;

    @Override
    public List<OrderListDto> getOrderLists(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(()-> new UsernameNotFoundException("유저를 찾을 수 없습니다"));

        Long userId = user.getId();
        List<OrderList> orderLists = orderListRepository.findAllByUserId(userId);
        List<OrderListDto> orderListDtos =
                orderLists.stream().map(
                        orderList -> {
                            try {
                                return OrderListMapper.mapToDto(orderList);
                            } catch (SQLException e) {
                                throw new RuntimeException(e);
                            }
                        }
                ).collect(Collectors.toList());

        return orderListDtos;
    }

    @Override
    public void save(Long productId,Long productCount,String username) throws SQLException {

        Product product = productRepository.findById(productId)
                .orElseThrow(()->new ResourceNotFoundException("product","해당 물품을 찾을 수 없습니다.",productId));
        ProductDto productDto = ProductMapper.mapToDto(product);

        User user = userRepository.findByUsername(username)
                .orElseThrow(()-> new UsernameNotFoundException("유저를 찾을 수 없습니다"));

        UserDto userDto = UserMapper.mapToDto(user);

        Long userId = user.getId();

        if(orderListRepository.existsByProductIdAndUserId(productId,userId))
        {
            OrderList orderList = orderListRepository.findByProductIdAndUserId(productId,userId)
                    .orElseThrow(()->new ResourceNotFoundException("product","해당 물품을 찾을 수 없습니다.",productId));

            Long savedProductCount =  productCount + orderList.getProductCount();

            orderListRepository.setProductCount(savedProductCount,productId,userId);
        }
        else
        {
            OrderListDto orderListDto = new OrderListDto();
            orderListDto.setUser(userDto);
            orderListDto.setProductCount(productCount);
            orderListDto.setProduct(productDto);
            try {
                orderListRepository.save(OrderListMapper.mapToEntity(orderListDto));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

    }

    @Override
    public void saveAll(CartViewDto[] cartViewDtos) {

        for(int i = 0; i< cartViewDtos.length ; i++)
        {
            OrderListDto orderListDto = new OrderListDto();

            Long productId = cartViewDtos[i].getProduct().getId();
            Long userId = cartViewDtos[i].getUser().getId();

            if(orderListRepository.existsByProductIdAndUserId(productId,userId))
            {
                OrderList orderList = orderListRepository.findByProductIdAndUserId(productId,userId)
                        .orElseThrow(()->new ResourceNotFoundException("product","해당 물품을 찾을 수 없습니다.",productId));

                Long productCount = orderList.getProductCount();
                Long savedProductCount =  productCount + cartViewDtos[i].getProductCount();

                orderListRepository.setProductCount(savedProductCount,productId,userId);

            }
            else
            {
                orderListDto.setUser(cartViewDtos[i].getUser());
                orderListDto.setProductCount(cartViewDtos[i].getProductCount());
                orderListDto.setProduct(cartViewDtos[i].getProduct());
                try {
                    orderListRepository.save(OrderListMapper.mapToEntity(orderListDto));
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }

        }


    }


}
