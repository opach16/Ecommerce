package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.dto.ProductDto;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/v1/products")
public class ProductController {

    @GetMapping
    public List<ProductDto> getAllProducts() {
        return new ArrayList<>();
    }

    @GetMapping(value = "{productId}")
    public ProductDto getProductById(@PathVariable Long productId) {
        return new ProductDto(1L, 6L, "test", "testDesc", new BigDecimal(150), 30);
    }

    @PostMapping
    public void addProduct(ProductDto productDto) {

    }

    @PutMapping
    public ProductDto updateProduct(ProductDto productDto) {
        return new ProductDto(2L, 4L, "updated", "updatedDesc", new BigDecimal(4), 12);
    }

    @DeleteMapping("/{productId}")
    public void deleteProduct(@PathVariable Long productId) {

    }
}
