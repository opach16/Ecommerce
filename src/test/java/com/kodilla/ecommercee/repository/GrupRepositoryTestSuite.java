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
public class GrupRepositoryTestSuite {

    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private ProductRepository productRepository;

    @BeforeEach
    void setUp() {
        groupRepository.deleteAll();
        productRepository.deleteAll();
    }

    @DisplayName("Should save a group and verify it exists with correct details in the repository")
    @Test
    void testSaveGroup() {
        //given
        Group group = new Group("group", "description");
        //when
        Group savedGruop = groupRepository.save(group);
        Optional<Group> retrievedGruop = groupRepository.findById(savedGruop.getId());
        //then
        assertTrue(retrievedGruop.isPresent());
        assertEquals(group.getId(), retrievedGruop.get().getId());
        assertEquals(group.getName(), retrievedGruop.get().getName());
        assertEquals(group.getDescription(), retrievedGruop.get().getDescription());
    }

    @DisplayName("Should retrieve all groups from the repository")
    @Test
    void testGetAllGroups() {
        //given
        Group group1 = new Group("group1", "description1");
        Group group2 = new Group("group2", "description2");
        groupRepository.save(group1);
        groupRepository.save(group2);
        //when
        List<Group> retrievedGroups = groupRepository.findAll();
        //then
        assertEquals(2, retrievedGroups.size());
    }

    @DisplayName("Should update group name and description")
    @Test
    void testUpdateGroup() {
        //given
        Group group = new Group("group", "description");
        Group savedGroup = groupRepository.save(group);
        //when
        savedGroup.setName("new name");
        savedGroup.setDescription("new description");
        groupRepository.save(savedGroup);
        Optional<Group> retrievedGroup = groupRepository.findById(savedGroup.getId());
        //then
        assertEquals(savedGroup.getId(), retrievedGroup.get().getId());
        assertEquals(savedGroup.getName(), retrievedGroup.get().getName());
        assertEquals(savedGroup.getDescription(), retrievedGroup.get().getDescription());
    }

    @DisplayName("Should delete an empty group")
    @Test
    void testDeleteEmptyGroup() {
        //given
        Group group = new Group("group", "description");
        Group savedGroup = groupRepository.save(group);
        //when
        groupRepository.delete(savedGroup);
        Optional<Group> retrievedGroup = groupRepository.findById(savedGroup.getId());
        //then
        assertTrue(retrievedGroup.isEmpty());
    }

    @DisplayName("Should delete groups and detach products, setting their group to null")
    @Test
    void testDeleteGroupWithProducts() {
        //given
        Group group = new Group("group", "description");
        Group savedGroup = groupRepository.save(group);
        Product product = new Product("product", "description", new BigDecimal(1), 1, group);
        Product savedProduct = productRepository.save(product);
        //when
        groupRepository.deleteById(savedGroup.getId());
        Optional<Product> retrievedProduct = productRepository.findById(savedProduct.getId());
        //then
        assertNull(retrievedProduct.get().getGroup());
    }

    @DisplayName("Should correctly add products to groups and retrieve them by group ID")
    @Test
    void testAddProducts() {
        //given
        Group group1 = new Group("group1", "description1");
        Group group2 = new Group("group2", "description2");
        Group savedGroup1 = groupRepository.save(group1);
        Group savedGroup2 = groupRepository.save(group2);
        Product product1 = new Product("product1", "description1", new BigDecimal("1.00"), 1, group1);
        Product product2 = new Product("product2", "description2", new BigDecimal("2.00"), 2, group1);
        Product product3 = new Product("product3", "description3", new BigDecimal("3.00"), 3, group1);
        Product product4 = new Product("product4", "description4", new BigDecimal("4.00"), 4, group2);
        productRepository.save(product1);
        productRepository.save(product2);
        productRepository.save(product3);
        productRepository.save(product4);
        //when
        Optional<Group> retrievedGroup1 = groupRepository.findById(savedGroup1.getId());
        Optional<Group> retrievedGroup2 = groupRepository.findById(savedGroup2.getId());
        Product retrievedProduct1 = retrievedGroup1.get().getProducts().get(0);
        //then
        assertEquals(3, retrievedGroup1.get().getProducts().size());
        assertEquals(1, retrievedGroup2.get().getProducts().size());
        assertEquals(product1.getId(), retrievedProduct1.getId());
    }
}
