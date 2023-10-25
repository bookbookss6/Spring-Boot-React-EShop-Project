package com.eshopbackend.service;

import com.eshopbackend.payload.Category;
import com.eshopbackend.payload.ProductDto;

import java.sql.SQLException;
import java.util.List;

public interface ProductService {

    void save(ProductDto productDto) throws SQLException;
    void delete(Long id);
    List<ProductDto> findAll(int page);
    ProductDto findById(Long id) throws SQLException;
    List<ProductDto> findByCategory(int page , Category category);
    List<ProductDto> findByKeywordContains(int page ,String keyword);
    List<ProductDto> findByCategoryAndKeywordContains(int page , Category category,String keyword);

}
