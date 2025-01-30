package com.kodilla.ecommercee.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Builder
@AllArgsConstructor
@Getter
public class CartDto {
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
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
