package com.eshopbackend.repository;

import com.eshopbackend.entity.Product;
import com.eshopbackend.payload.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {

    Page<Product> findByCategory(Pageable pageable, Category category);
    Page<Product> findByTitleContains(Pageable pageable, String title);
    Page<Product> findByCategoryAndTitleContains(Pageable pageable, Category category,String Title);

}
