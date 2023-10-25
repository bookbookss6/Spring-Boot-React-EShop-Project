package com.eshopbackend.service.impl;

import com.eshopbackend.entity.CartView;
import com.eshopbackend.entity.Product;
import com.eshopbackend.entity.User;
import com.eshopbackend.exception.ResourceNotFoundException;
import com.eshopbackend.mapper.CartViewMapper;
import com.eshopbackend.payload.CartViewDto;
import com.eshopbackend.repository.CartViewRepository;
import com.eshopbackend.repository.ProductRepository;
import com.eshopbackend.repository.UserRepository;
import com.eshopbackend.service.CartViewService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CartViewServiceImpl implements CartViewService {

    private CartViewRepository cartViewRepository;
    private ProductRepository productRepository;
    private UserRepository userRepository;

    @Override
    public void save(Long productId, String username) throws SQLException {
        CartView cartView = new CartView();
        Product product = productRepository.findById(productId).
                orElseThrow(()->new ResourceNotFoundException("product","해당 물품을 찾을 수 없습니다.",productId));

        User user = userRepository.findByUsername(username)
                .orElseThrow(()-> new UsernameNotFoundException("유저를 찾을 수 없습니다"));

        Long userId = user.getId();

        cartView.setProduct(product);
        cartView.setUser(user);


        if(cartViewRepository.existsByProductIdAndUserId(productId,userId))
        {
            Long productCount = cartViewRepository.findByProductIdAndUserId(productId,userId)
                            . orElseThrow(()->new ResourceNotFoundException("cartview","해당 물품과 유저를 찾을 수 없습니다.",productId))
                                    .getProductCount();

            cartViewRepository.setProductCount
                    (productCount+1L,productId,userId);
            
        }
        else {
            cartView.setProductCount(1L);
            CartViewMapper.mapToDto(cartViewRepository.save(cartView));
        }

    }

    @Override
    public void delete(Long productId, String username) {

        User user = userRepository.findByUsername(username)
                .orElseThrow(()-> new UsernameNotFoundException("유저를 찾을 수 없습니다"));

        CartView cartView = cartViewRepository.findByProductIdAndUserId(productId,user.getId())
                .orElseThrow(()->new ResourceNotFoundException("product","해당 물품을 찾을 수 없습니다.",productId));

        cartViewRepository.delete(cartView);
    }

    @Override
    public void deleteAll(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(()-> new UsernameNotFoundException("유저를 찾을 수 없습니다"));

        List<CartView> cartViews = cartViewRepository.findByUserId(user.getId())
                .orElseThrow(()-> new UsernameNotFoundException("유저를 찾을 수 없습니다"));;

        for(CartView cartView : cartViews)
        {
            cartViewRepository.delete(cartView);
        }
    }

    @Override
    public List<CartViewDto> getCartViews(String username) {

        User user = userRepository.findByUsername(username)
                .orElseThrow(()-> new UsernameNotFoundException("유저를 찾을 수 없습니다"));

        List<CartView> cartViews = cartViewRepository.findByUserId(user.getId()).
                orElseThrow(()->new ResourceNotFoundException("userId","해당 유저를 찾을 수 없습니다.",user.getId()));

        List<CartViewDto> cartViewDtos =
                cartViews.stream().map( cartView ->
                                {
                                    try {
                                        return CartViewMapper.mapToDto(cartView);
                                    } catch (SQLException e) {
                                        throw new RuntimeException(e);
                                    }
                                }
                        )
                        .collect(Collectors.toList());

        return cartViewDtos;
    }

    @Override
    public void setProductCount(Long productCount, Long productId, String username) {

        User user = userRepository.findByUsername(username)
                .orElseThrow(()-> new UsernameNotFoundException("유저를 찾을 수 없습니다"));

        Long userId = user.getId();

        if(productCount <= 0)
        {
            CartView cartView = cartViewRepository.findByProductIdAndUserId(productId,user.getId())
                    .orElseThrow(()->new ResourceNotFoundException("product","해당 물품을 찾을 수 없습니다.",productId));

            cartViewRepository.delete(cartView);
            return;
        }
        if(cartViewRepository.existsByProductIdAndUserId(productId,userId))
        {
            cartViewRepository.setProductCount
                    (productCount, productId, userId);
        }

    }


}
