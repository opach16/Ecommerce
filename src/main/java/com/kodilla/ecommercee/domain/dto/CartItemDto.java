package com.kodilla.ecommercee.domain.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CartItemDto {
    private Long id;
    private Long cartId;
    private Long productId;
    private Integer quantity;
}
