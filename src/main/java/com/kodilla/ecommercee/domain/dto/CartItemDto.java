package com.kodilla.ecommercee.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CartItemDto {
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;
    private Long cartId;
    private Long productId;
    private Integer quantity;

    public CartItemDto(Long cartId, Long productId, Integer quantity) {
        this.cartId = cartId;
        this.productId = productId;
        this.quantity = quantity;
    }
}
