package com.kodilla.ecommercee.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Builder
@AllArgsConstructor
@Getter
public class CartDto {
    private Long id;
    private Long userId;
    private boolean ordered;
    private BigDecimal totalPrice;

    public CartDto(Long userId, boolean ordered, BigDecimal totalPrice) {
        this.userId = userId;
        this.ordered = ordered;
        this.totalPrice = totalPrice;
    }
}
