package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.dto.CartDto;
import com.kodilla.ecommercee.domain.dto.CartItemDto;
import com.kodilla.ecommercee.domain.dto.OrderDto;
import com.kodilla.ecommercee.exception.CartItemNotFoundException;
import com.kodilla.ecommercee.exception.CartNotFoundException;
import com.kodilla.ecommercee.exception.ProductNotFoundException;
import com.kodilla.ecommercee.service.CartService;
import com.kodilla.ecommercee.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("v1/carts")
public class CartController {
    private final CartService cartService;
    private final OrderService orderService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CartDto> createCart(@RequestBody CartDto cartDto) {
        return ResponseEntity.ok(cartService.createCart(cartDto));
    }

    @GetMapping("/{cartId}/items")
    public ResponseEntity<List<CartItemDto>> getCartItems(@PathVariable Long cartId) {
        return ResponseEntity.ok(cartService.getAllCartItems(cartId));
    }

    @PostMapping(value = "/addItem", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CartItemDto> addCartItem(@RequestBody CartItemDto cartItemDto) throws CartNotFoundException, ProductNotFoundException {
        return ResponseEntity.ok(cartService.addCartItem(cartItemDto));
    }

    @DeleteMapping(value = "/item/{cartItemId}")
    public ResponseEntity<Void> deleteCartItem(@PathVariable Long cartItemId) throws CartItemNotFoundException {
        cartService.deleteCartItemById(cartItemId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{cartId}/create-order")
    public ResponseEntity<OrderDto> createOrder(@PathVariable Long cartId) throws CartNotFoundException {
        return ResponseEntity.ok(orderService.createOrder(cartId));
    }
}
