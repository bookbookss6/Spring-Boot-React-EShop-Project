package com.eshopbackend.repository;

import com.eshopbackend.entity.OrderList;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface OrderListRepository extends JpaRepository<OrderList,Long> {

    List<OrderList> findAllByUserId(Long userId);
    Optional<OrderList> findByProductIdAndUserId(Long productId, Long userId);
    boolean existsByProductIdAndUserId(Long productId,Long userId);
    void deleteByProductId(Long id);
    @Transactional
    @Modifying
    @Query(value = "update orderlist o set o.product_count = :productCount" +
            " where o.product_id = :productId and o.user_id = :userId" , nativeQuery = true)
    int setProductCount(
            @Param(value = "productCount") Long productCount,
            @Param(value = "productId") Long productId,
            @Param(value = "userId") Long userId);

}
