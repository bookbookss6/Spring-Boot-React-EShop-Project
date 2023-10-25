package com.eshopbackend.controller;

import com.eshopbackend.payload.Category;
import com.eshopbackend.payload.ProductDto;
import com.eshopbackend.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping("/save")
    public void saveProduct(@RequestBody ProductDto productDto) throws SQLException {
        productService.save(productDto);
    }
    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping("/delete")
    public void deleteProduct
            (@RequestParam(value = "id")Long id)
    {
        productService.delete(id);
    }

    @GetMapping("/{page}")
    public List<ProductDto> getProducts
            (@PathVariable(value = "page") int page,
             @RequestParam(value = "category" , required = false)String category,
             @RequestParam(value = "title" , required = false)String title)
    {

        Category enumCategory = null;
        if(category != null && !category.equals("total")) {
            enumCategory = Category.valueOf(category.toUpperCase());
        }

        if(category == null && title == null)
        {
            return productService.findAll(page);
        }

        if(category.equals("total"))
        {
            if(title.isEmpty())
            {
                return productService.findAll(page);
            }
            else{
                return productService.findByKeywordContains(page,title);
            }
        }
        else{
            if(title.isEmpty())
            {
                return productService.findByCategory(page,enumCategory);
            }
            else{
                return productService.findByCategoryAndKeywordContains(page,enumCategory,title);
            }
        }

    }

    @GetMapping("/detail/{id}")
    public ProductDto getProductById
            (@PathVariable(value = "id")Long id) throws SQLException {
        return productService.findById(id);
    }

}
