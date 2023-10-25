package com.eshopbackend.mapper;

import com.eshopbackend.payload.ProductDto;
import com.eshopbackend.entity.Product;

import javax.sql.rowset.serial.SerialBlob;
import java.sql.Blob;
import java.sql.SQLException;

public class ProductMapper {

    public static ProductDto mapToDto(Product product) throws SQLException {
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setImage(product.getImage().getBytes(1,(int)product.getImage().length()));
        productDto.setPrice(product.getPrice());
        productDto.setCategory(product.getCategory());
        productDto.setTitle(product.getTitle());

        return productDto;
    }

    public static Product mapToEntity(ProductDto productDto) throws SQLException {
        Blob blob = new SerialBlob(productDto.getImage());

        Product product = new Product();
        product.setId(productDto.getId());
        product.setImage(blob);
        product.setPrice(productDto.getPrice());
        product.setCategory(productDto.getCategory());
        product.setTitle(productDto.getTitle());

        return product;
    }

}
