package com.eshopbackend.controller;

import com.eshopbackend.payload.CartViewDto;
import com.eshopbackend.payload.OrderListDto;
import com.eshopbackend.service.OrderListService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/order-list")
public class OrderListController {

    private OrderListService orderListService;

    @PreAuthorize("hasAnyRole('USER')")
    @PostMapping("/save/product-id={product-id}/product-count={product-count}/username={username}")
    public void saveOrderList
            (@PathVariable(value = "product-id") Long productId,
             @PathVariable(value = "product-count") Long productCount,
             @PathVariable(value = "username") String username) throws SQLException {

        orderListService.save(productId,productCount,username);
    }

    @PreAuthorize("hasAnyRole('USER')")
    @PostMapping("/save-all")
    public void saveOrderLists(@RequestBody CartViewDto[] cartViewDtos)
    {
        orderListService.saveAll(cartViewDtos);
    }

    @PreAuthorize("hasAnyRole('USER')")
    @GetMapping("/username={username}")
    public ResponseEntity<List<OrderListDto>> getOrderLists
            (@PathVariable(value = "username")String username)
    {
        return new ResponseEntity<>(orderListService.getOrderLists(username) , HttpStatus.OK);
    }

}
