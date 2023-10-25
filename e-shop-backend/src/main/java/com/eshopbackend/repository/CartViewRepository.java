package com.eshopbackend.repository;

import com.eshopbackend.entity.CartView;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;


public interface CartViewRepository extends JpaRepository<CartView,Long> {

    boolean existsByProductIdAndUserId(Long productId,Long userId);
    void deleteByProductId(Long id);
    Optional<CartView> findByProductIdAndUserId(Long productId,Long userId);
    Optional<List<CartView>> findByUserId(Long userId);

    @Transactional
    @Modifying
    @Query(value = "update cartview c set c.product_count = :productCount" +
            " where c.product_id = :productId and c.user_id = :userId" , nativeQuery = true)
    int setProductCount(
            @Param(value = "productCount") Long productCount,
            @Param(value = "productId") Long productId,
            @Param(value = "userId") Long userId);

}
