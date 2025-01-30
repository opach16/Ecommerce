package com.kodilla.ecommercee.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDto {
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;
    private Long groupId;
    private String name;
    private String description;
    private BigDecimal price;
    private Integer stock;

    public ProductDto(Long groupId, String name, String description, BigDecimal price, Integer stock) {
        this.groupId = groupId;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
    }
}
