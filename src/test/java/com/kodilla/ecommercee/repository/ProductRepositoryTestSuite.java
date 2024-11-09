package com.kodilla.ecommercee.repository;

import com.kodilla.ecommercee.domain.Group;
import com.kodilla.ecommercee.domain.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ProductRepositoryTestSuite {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private GroupRepository groupRepository;

    @BeforeEach
    void setUp() {
        productRepository.deleteAll();
        groupRepository.deleteAll();
    }

    @DisplayName("Should save a product and verify it exists with correct details in the repository")
    @Test
    void testAddProduct() {
        //given
        Product product = new Product("product", "description", new BigDecimal("1.00"), 1, null);
        //when
        Product savedProduct = productRepository.save(product);
        Optional<Product> retrievedProduct = productRepository.findById(savedProduct.getId());
        //then
        assertTrue(retrievedProduct.isPresent());
        assertEquals(savedProduct.getId(), retrievedProduct.get().getId());
        assertEquals(savedProduct.getDescription(), retrievedProduct.get().getDescription());
        assertEquals(savedProduct.getPrice(), retrievedProduct.get().getPrice());
        assertEquals(savedProduct.getStock(), retrievedProduct.get().getStock());
        assertNull(retrievedProduct.get().getGroup());
        assertEquals(savedProduct.getCreatedAt(), retrievedProduct.get().getCreatedAt());
    }

    @DisplayName("Should update product details and verify changes with the repository")
    @Test
    void testUpdateProduct() {
        //given
        Group group = new Group("group", "description");
        groupRepository.save(group);
        Product product = new Product("product", "description", new BigDecimal("1.00"), 1, null);
        Product savedProduct = productRepository.save(product);
        //when
        savedProduct.setName("new name");
        savedProduct.setDescription("new description");
        savedProduct.setPrice(new BigDecimal("2.00"));
        savedProduct.setStock(2);
        savedProduct.setGroup(group);
        productRepository.save(savedProduct);
        Optional<Product> retrievedProduct = productRepository.findById(savedProduct.getId());
        //then
        assertEquals(savedProduct.getId(), retrievedProduct.get().getId());
        assertEquals(savedProduct.getName(), retrievedProduct.get().getName());
        assertEquals(savedProduct.getDescription(), retrievedProduct.get().getDescription());
        assertEquals(savedProduct.getPrice(), retrievedProduct.get().getPrice());
        assertEquals(savedProduct.getStock(), retrievedProduct.get().getStock());
        assertEquals(savedProduct.getGroup().getId(), retrievedProduct.get().getGroup().getId());
    }

    @DisplayName("Should delete a product from the repository")
    @Test
    void testDeleteProduct() {
        //given
        Product product = new Product("product", "description", new BigDecimal("1.00"), 1, null);
        Product savedProduct = productRepository.save(product);
        //when
        productRepository.delete(savedProduct);
        Optional<Product> retrievedProduct = productRepository.findById(savedProduct.getId());
        //then
        assertTrue(retrievedProduct.isEmpty());
    }

    @DisplayName("Should retrieve all products from the repository")
    @Test
    void testGetAllProducts() {
        //given
        Product product1 = new Product("product", "description", new BigDecimal("1.00"), 1, null);
        Product product2 = new Product("product2", "description2", new BigDecimal("2.00"), 2, null);
        Product product3 = new Product("product3", "description3", new BigDecimal("3.00"), 3, null);
        productRepository.save(product1);
        productRepository.save(product2);
        productRepository.save(product3);
        //when
        List<Product> products = productRepository.findAll();
        //then
        assertEquals(3, products.size());
    }

    @DisplayName("Should remove a product from the group list after the product is deleted")
    @Test
    void testDeleteProductsFromGroup() {
        //given
        Group group = new Group("group", "description");
        Group savedGroup = groupRepository.save(group);
        Product product1 = new Product("product1", "description1", new BigDecimal("1.00"), 1, group);
        Product product2 = new Product("product2", "description2", new BigDecimal("2.00"), 2, group);
        productRepository.save(product1);
        productRepository.save(product2);
        Optional<Group> retrievedGroupBeforeDeletion = groupRepository.findById(savedGroup.getId());
        //when
        productRepository.delete(product1);
        Optional<Group> retrievedGroupAfterDeletion = groupRepository.findById(savedGroup.getId());
        //then
        assertEquals(2, retrievedGroupBeforeDeletion.get().getProducts().size());
        assertEquals(1, retrievedGroupAfterDeletion.get().getProducts().size());
    }
}
