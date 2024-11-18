package com.kodilla.ecommercee.controller;


import com.kodilla.ecommercee.domain.dto.CartDto;
import com.kodilla.ecommercee.domain.dto.CartItemDto;
import com.kodilla.ecommercee.exception.CartItemNotFoundException;
import com.kodilla.ecommercee.exception.CartNotFoundException;
import com.kodilla.ecommercee.exception.ProductNotFoundException;
import com.kodilla.ecommercee.service.CartService;
import com.kodilla.ecommercee.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("v1/carts")
public class CartController {
    private final CartService cartService;
    private final OrderService orderService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createCart(@RequestBody CartDto cartDto) {
        cartService.createCart(cartDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{cartId}/items")
    public ResponseEntity<List<CartItemDto>> getCartItems(@PathVariable Long cartId)  {
        List<CartItemDto> cartItemDtos = cartService.getAllCartItems(cartId);
        return ResponseEntity.ok(cartItemDtos);
    }

    @PostMapping(value = "/{cartId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> addCartItem(@PathVariable Long cartId, @RequestBody CartItemDto cartItemDto) throws CartNotFoundException, ProductNotFoundException {
        cartService.addCartItem(cartId, cartItemDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value = "/item/{cartItemId}")
    public ResponseEntity<Void> deleteCartItem(@PathVariable Long cartItemId)  {
        cartService.deleteCartItemById(cartItemId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{cartId}/createorder")
    public ResponseEntity<Void> createOrder(@PathVariable Long cartId) throws CartNotFoundException {
        orderService.createOrder(cartId);
        return ResponseEntity.ok().build();
    }
}
