package com.kodilla.ecommercee.domain.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@AllArgsConstructor
@Getter
public class CartDto {
    private Long id;
    private Long userId;
    private boolean ordered;
    private BigDecimal totalPrice;
}
