package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.domain.dto.ProductDto;
import com.kodilla.ecommercee.exception.ProductNotFoundException;
import com.kodilla.ecommercee.mapper.ProductMapper;
import com.kodilla.ecommercee.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public List<ProductDto> getAllProducts() {
        List<Product> retrievedProducts = productRepository.findAll();
        return productMapper.mapToProductDtoList(retrievedProducts);
    }

    public ProductDto getProductById(Long id) throws ProductNotFoundException {
        Product product = productRepository.findById(id).orElseThrow(ProductNotFoundException::new);
        return productMapper.mapToProductDto(product);
    }

    public ProductDto addProduct(ProductDto productDto) {
        Product savedProduct = productRepository.save(productMapper.mapToProductEntity(productDto));
        return productMapper.mapToProductDto(savedProduct);
    }

    public ProductDto updateProduct(ProductDto productDto) throws ProductNotFoundException {
        Product retrievedProduct = productRepository.findById(productDto.getId())
                .orElseThrow(ProductNotFoundException::new);
        retrievedProduct.setName(productDto.getName());
        retrievedProduct.setDescription(productDto.getDescription());
        retrievedProduct.setPrice(productDto.getPrice());
        return productMapper.mapToProductDto(productRepository.save(retrievedProduct));
    }

    public void deleteProduct(Long id) throws ProductNotFoundException {
        Product product = productRepository.findById(id).orElseThrow(ProductNotFoundException::new);
        productRepository.delete(product);
    }
}
