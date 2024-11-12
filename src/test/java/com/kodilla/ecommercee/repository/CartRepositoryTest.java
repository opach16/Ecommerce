package com.kodilla.ecommercee.repository;

import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.CartItem;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.domain.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CartRepositoryTest {
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CartItemRepository cartItemRepository;
    @Autowired
    private UserRepository userRepository;

    @AfterEach
    void clearUp() {
        userRepository.deleteAll();
        cartRepository.deleteAll();
        productRepository.deleteAll();
        cartItemRepository.deleteAll();
    }

    @Test
    void shouldSaveCart() {
        //given
        User user = new User("fname", "lname", "email", "uname", "pass", "123!");
        Cart cart = new Cart(user);
        userRepository.save(user);
        cartRepository.save(cart);

        //when
        Long id = cart.getId();
        Optional<Cart> cartOptional = cartRepository.findById(id);

        //then
        assertTrue(cartOptional.isPresent());
        assertEquals(id, cartOptional.get().getId());
    }

    @Test
    void shouldGetAllItemFromCart() {
        //given
        Product product1 = new Product("product", "description", new BigDecimal("1.00"), 1, null);
        Product product2 = new Product("product2", "description2", new BigDecimal("2.00"), 2, null);
        productRepository.save(product1);
        productRepository.save(product2);
        User user = new User("fname", "lname", "email", "uname", "pass", "123!");
        userRepository.save(user);
        Cart cart = new Cart(user);
        CartItem cartItem1 = new CartItem(cart, 15, product1);
        CartItem cartItem2 = new CartItem(cart, 15, product2);
        cart.addCartItem(cartItem1);
        cart.addCartItem(cartItem2);
        cartRepository.save(cart);

        //when
        Long id = cart.getId();
        Optional<Cart> cartOptional = cartRepository.findById(id);

        //then
        assertEquals(2,cartOptional.get().getCartItems().size());

    }
    @Test
    void shouldAddItemToCart() {
        //given
        Product product1 = new Product("product", "description", new BigDecimal("1.00"), 1, null);
        Product product2 = new Product("product2", "description2", new BigDecimal("2.00"), 2, null);
        productRepository.save(product1);
        productRepository.save(product2);
        User user = new User("fname", "lname", "email", "uname", "pass", "123!");
        userRepository.save(user);
        Cart cart = new Cart(user);
        CartItem cartItem1 = new CartItem(cart, 15, product1);
        cart.addCartItem(cartItem1);
        cartRepository.save(cart);

        //when
        Long id = cart.getId();
        Optional<Cart> cartOptional = cartRepository.findById(id);

        //then
        assertEquals(1,cartOptional.get().getCartItems().size());
    }

    @Test
    void shouldRemoveItemFromCart() {
        //given
        Product product1 = new Product("product", "description", new BigDecimal("1.00"), 1, null);
        Product product2 = new Product("product2", "description2", new BigDecimal("2.00"), 2, null);
        productRepository.save(product1);
        productRepository.save(product2);
        User user = new User("fname", "lname", "email", "uname", "pass", "123!");
        userRepository.save(user);
        Cart cart = new Cart(user);
        CartItem cartItem1 = new CartItem(cart, 15, product1);
        CartItem cartItem2 = new CartItem(cart, 15, product2);
        cart.addCartItem(cartItem1);
        cart.addCartItem(cartItem2);
        cartRepository.save(cart);
        cart.removeCartItem(cartItem1);
        cartRepository.save(cart);

        //when
        Long id = cart.getId();
        Optional<Cart> result = cartRepository.findById(id);

        //then
        assertEquals(1,result.get().getCartItems().size());
        assertFalse(result.get().getCartItems().contains(cartItem2));
    }

}