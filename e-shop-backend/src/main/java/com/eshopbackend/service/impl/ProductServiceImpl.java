package com.eshopbackend.service.impl;

import com.eshopbackend.exception.ResourceNotFoundException;
import com.eshopbackend.payload.Category;
import com.eshopbackend.payload.ProductDto;
import com.eshopbackend.entity.Product;
import com.eshopbackend.mapper.ProductMapper;
import com.eshopbackend.repository.CartViewRepository;
import com.eshopbackend.repository.OrderListRepository;
import com.eshopbackend.repository.ProductRepository;
import com.eshopbackend.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;
    private CartViewRepository cartViewRepository;
    private OrderListRepository orderListRepository;

    @Override
    public void save(ProductDto productDto) throws SQLException {
        Product product = ProductMapper.mapToEntity(productDto);
        productRepository.save(product);
    }

    @Override
    public void delete(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("product","해당 물품을 찾을 수 없습니다.",id));

        orderListRepository.deleteByProductId(id);
        cartViewRepository.deleteByProductId(id);
        productRepository.delete(product);

    }

    @Override
    public List<ProductDto> findAll(int page) {

        int pageNumber =  page - 1;
        int pageSize = 9;

        Pageable pageable = PageRequest.of(pageNumber,pageSize);

        Page<Product> products = productRepository.findAll(pageable);
        List<Product> listOfProducts = products.getContent();

        return listOfProducts.stream()
                .map(product -> {
                    try {
                        return ProductMapper.mapToDto(product);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                })
                .collect(Collectors.toList());
    }

    @Override
    public ProductDto findById(Long id) throws SQLException {

        Product product = productRepository.
                findById(id).orElseThrow(()-> new ResourceNotFoundException("product","id",id));

        return ProductMapper.mapToDto(product);
    }

    @Override
    public List<ProductDto> findByCategory(int page , Category category) {
        int pageNumber =  page - 1;
        int pageSize = 9;

        Pageable pageable = PageRequest.of(pageNumber,pageSize);
        Page<Product> products = productRepository.findByCategory(pageable,category);

        List<Product> listOfProducts = products.getContent();

        return listOfProducts.stream()
                .map(product -> {
                    try {
                        return ProductMapper.mapToDto(product);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductDto> findByKeywordContains(int page, String title) {
        int pageNumber =  page - 1;
        int pageSize = 9;

        Pageable pageable = PageRequest.of(pageNumber,pageSize);
        Page<Product> products = productRepository.findByTitleContains(pageable,title);

        List<Product> listOfProducts = products.getContent();

        return listOfProducts.stream()
                .map(product -> {
                    try {
                        return ProductMapper.mapToDto(product);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductDto> findByCategoryAndKeywordContains(int page , Category category, String title) {
        int pageNumber =  page - 1;
        int pageSize = 9;

        Pageable pageable = PageRequest.of(pageNumber,pageSize);
        Page<Product> products = productRepository.findByCategoryAndTitleContains(pageable,category,title);

        List<Product> listOfProducts = products.getContent();

        return listOfProducts.stream()
                .map(product -> {
                    try {
                        return ProductMapper.mapToDto(product);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                })
                .collect(Collectors.toList());
    }
}
