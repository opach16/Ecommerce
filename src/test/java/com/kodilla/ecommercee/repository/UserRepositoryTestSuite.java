package com.kodilla.ecommercee.repository;

import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.domain.User;
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
public class UserRepositoryTestSuite {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CartDao cartDao;

    @Autowired
    private OrderDao orderDao;

    @BeforeEach
    void setUp() {
        userRepository.deleteAll();
    }

    @DisplayName("Should save a user and verify it exists with correct details in the repository")
    @Test
    void addUser() {
        //given
        User user = new User("fname", "lname", "email", "uname", "pass", "123!");
        //when
        User savedUser = userRepository.save(user);
        Optional<User> retrievedUser = userRepository.findById(savedUser.getId());
        //then
        assertTrue(retrievedUser.isPresent());
        assertEquals(savedUser.getId(), retrievedUser.get().getId());
        assertEquals(savedUser.getFirstName(), retrievedUser.get().getFirstName());
        assertEquals(savedUser.getLastName(), retrievedUser.get().getLastName());
        assertEquals(savedUser.getEmail(), retrievedUser.get().getEmail());
        assertEquals(savedUser.getPassword(), retrievedUser.get().getPassword());
        assertEquals(savedUser.getAccessKey(), retrievedUser.get().getAccessKey());
        assertFalse(retrievedUser.get().isBlocked());
        assertEquals(savedUser.getCreatedAt(), retrievedUser.get().getCreatedAt());
    }


    @DisplayName("Should update user details and verify changes with the repository")
    @Test
    void updateUser() {
        //given
        User user = new User("fname", "lname", "email", "uname", "pass", "123!");
        User savedUser = userRepository.save(user);
        //when
        savedUser.setFirstName("updatedFirstName");
        savedUser.setLastName("updatedLastName");
        savedUser.setEmail("updatedEmail");
        savedUser.setPassword("updatedPassword");
        savedUser.setAccessKey("updatedAccessKey");
        savedUser.setBlocked(true);
        userRepository.save(savedUser);
        Optional<User> retrievedUser = userRepository.findById(savedUser.getId());
        //then
        assertEquals(savedUser.getId(), retrievedUser.get().getId());
        assertEquals(savedUser.getFirstName(), retrievedUser.get().getFirstName());
        assertEquals(savedUser.getLastName(), retrievedUser.get().getLastName());
        assertEquals(savedUser.getEmail(), retrievedUser.get().getEmail());
        assertEquals(savedUser.getPassword(), retrievedUser.get().getPassword());
        assertEquals(savedUser.getAccessKey(), retrievedUser.get().getAccessKey());
        assertTrue(retrievedUser.get().isBlocked());
    }

    @DisplayName("Should retrieve all users from the repository")
    @Test
    void getAllUsers() {
        //given
        User user1 = new User("fname1", "lname1", "email1", "uname1", "pass1", "123!1");
        User user2 = new User("fname2", "lname2", "email2", "uname2", "pass2", "123!2");
        userRepository.save(user1);
        userRepository.save(user2);
        //when
        List<User> retrievedUsers = userRepository.findAll();
        //then
        assertEquals(2, retrievedUsers.size());
    }

    @DisplayName("Should remove user from the repository")
    @Test
    void deleteEmptyUser() {
        //given
        User user = new User("fname", "lname", "email", "uname", "pass", "123!");
        User savedUser = userRepository.save(user);
        userRepository.deleteById(savedUser.getId());
        Optional<User> retrievedUser = userRepository.findById(savedUser.getId());
        assertTrue(retrievedUser.isEmpty());
    }

    @DisplayName("Should remove user and related carts and orders from the repository")
    @Test
    void deleteUserWithCartAndOrder() {
        //given
        User user = new User("fname", "lname", "email", "uname", "pass", "123!");
        User savedUser = userRepository.save(user);
        Cart cart = new Cart(savedUser);
        cart.setTotal(new BigDecimal("100.00"));
        Cart savedCart = cartDao.save(cart);
        Order order = new Order(savedCart, savedUser);
        Order savedOrder = orderDao.save(order);
        //when
        userRepository.deleteById(savedUser.getId());
        Optional<User> retrievedUser = userRepository.findById(savedUser.getId());
        Optional<Cart> retrievedCart = cartDao.findById(savedCart.getId());
        Optional<Order> retrievedOrder = orderDao.findById(savedOrder.getId());
        //then
        assertTrue(retrievedUser.isEmpty());
        assertTrue(retrievedCart.isEmpty());
        assertTrue(retrievedOrder.isEmpty());
    }
}
