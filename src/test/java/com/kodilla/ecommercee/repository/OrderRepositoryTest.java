package com.kodilla.ecommercee.repository;

import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

@SpringBootTest
class OrderRepositoryTest {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CartRepository cartRepository;

    @Test
    void shouldSaveOrder() {
        //given
        User user = new User(12L,"Marcin","Pajak","test@test.com","pele","password","false",false, LocalDateTime.now());
        userRepository.save(user);
        Cart cart = new Cart(user);
        cartRepository.save(cart);

        Order order = new Order("PREPARING", LocalDate.now(),  cart, user);
        //when
        orderRepository.save(order);
        //then
        Long id = order.getId();
        Optional<Order> orderOptional = orderRepository.findById(id);
        assertTrue(orderOptional.isPresent());
        assertEquals(order.getStatus(), orderOptional.get().getStatus());


        //clean up
        orderRepository.deleteById(id);
        cartRepository.deleteById(cart.getId());
        orderRepository.deleteById(user.getId());

    }
}