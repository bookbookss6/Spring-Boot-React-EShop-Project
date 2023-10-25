package com.eshopbackend.controller;

import com.eshopbackend.payload.CartViewDto;
import com.eshopbackend.service.CartViewService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/cartview")
public class CartViewController {

    private CartViewService cartViewService;

    @PreAuthorize("hasAnyRole('USER')")
    @PostMapping("/product-id={product-id}/username={username}")
    public void saveCartView
            (@PathVariable(value = "product-id")Long productId,
             @PathVariable(value = "username")String username) throws SQLException {

        cartViewService.save(productId,username);
    }

    @PreAuthorize("hasAnyRole('USER')")
    @GetMapping("/username={username}")
    public ResponseEntity<List<CartViewDto>> getCartViews
            (@PathVariable(value = "username")String username)
    {
        return new ResponseEntity<>(cartViewService.getCartViews(username) , HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('USER')")
    @GetMapping("/product-id={product-id}/username={username}")
    public void deleteCartView
            (@PathVariable(value = "product-id")Long productId,
             @PathVariable(value = "username")String username)
    {
        cartViewService.delete(productId,username);
    }

    @PreAuthorize("hasAnyRole('USER')")
    @DeleteMapping("/username={username}")
    public void deleteCartViews
            (@PathVariable(value = "username")String username)
    {
        cartViewService.deleteAll(username);
    }

    @PreAuthorize("hasAnyRole('USER')")
    @PostMapping("/product-id={product-id}/username={username}/product-count={product-count}")
    public void setProductCount
            (@PathVariable(value = "product-id")Long productId,
             @PathVariable(value = "username")String username,
             @PathVariable(value = "product-count")Long productCount)
    {
        cartViewService.setProductCount(productCount,productId,username);
    }

}
