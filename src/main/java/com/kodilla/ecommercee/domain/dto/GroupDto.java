package com.kodilla.ecommercee.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
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
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;
    private String name;
    private String description;
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private List<ProductDto> products;

    public GroupDto(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
