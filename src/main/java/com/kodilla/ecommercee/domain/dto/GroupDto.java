package com.kodilla.ecommercee.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GroupDto {
    private Long id;
    private String name;
    private String description;
    private List<ProductDto> products;

    public GroupDto(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }
}
