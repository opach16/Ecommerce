package com.kodilla.ecommercee.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
public class OrderDto {
    private Long id;
    private String status;
    private Long cartId;
    private LocalDate orderDate;
    private Long userId;

    public OrderDto(String status, Long cartId, LocalDate orderDate, Long userId) {
        this.status = status;
        this.cartId = cartId;
        this.orderDate = orderDate;
        this.userId = userId;
    }
}