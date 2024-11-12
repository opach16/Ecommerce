package com.kodilla.ecommercee.repository;

import com.kodilla.ecommercee.domain.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
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
    @Autowired
    private CartItemRepository cartItemRepository;
    @Autowired
    private ProductRepository productRepository;

    @AfterEach
    void cleanUp(){
        orderRepository.deleteAll();
        cartRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    void shouldSaveOrder() {
        //given
        User user = new User("Marcin","Pajak","test@test.com","pele","password","accesKey");
        userRepository.save(user);

        Cart cart = new Cart(user);
        cartRepository.save(cart);

        Order order = new Order(cart, user);
        //when
        orderRepository.save(order);
        Long id = order.getId();
        Optional<Order> orderOptional = orderRepository.findById(id);
        //then


        assertTrue(orderOptional.isPresent());
        assertEquals(order.getStatus(), orderOptional.get().getStatus());

    }
    @Test
    void shouldFindAllOrders(){
        //given
        User user = new User("Marcin","Pajak","test@test.com","pele","password", "accessKey");
        userRepository.save(user);

        Cart cart = new Cart(user);
        Cart cart2 = new Cart(user);
        cartRepository.save(cart);
        cartRepository.save(cart2);

        Order order = new Order(  cart, user);
        Order order2 = new Order(cart2, user);
        orderRepository.save(order);
        orderRepository.save(order2);
        //when
        List<Order> orders = orderRepository.findAll();
        //then
        assertEquals(2, orders.size());

    }
    @Test
    void shouldFindOrderById(){
        //given
        User user = new User("Marcin","Pajak","test@test.com","pele","password","accessKey");
        userRepository.save(user);
        Cart cart = new Cart(user);
        cartRepository.save(cart);
        Order order = new Order( cart, user);
        orderRepository.save(order);
        //when
        Optional<Order> orderOptional = orderRepository.findById(order.getId());
        //then
        assertTrue(orderOptional.isPresent());
        assertEquals(order.getStatus(), orderOptional.get().getStatus());
        assertEquals(order.getId(), orderOptional.get().getId());

    }
    @Test
    void shouldUpdateOrderWithNewData(){
        //given
        User user = new User("Marcin","Pajak","test@test.com","pele","password","accessKey");
        userRepository.save(user);
        Cart cart = new Cart(user);
        cartRepository.save(cart);
        Order order = new Order(  cart, user);
        orderRepository.save(order);
        //when
        order.changeStatus("SEND");
        orderRepository.save(order);

        //then
        Optional<Order> orderOptional = orderRepository.findById(order.getId());
        assertEquals("SEND", orderOptional.get().getStatus());
    }
    @Test
    void shouldDeleteOrder(){
        //given
        User user = new User("Marcin","Pajak","test@test.com","pele","password","accesKey");
        userRepository.save(user);
        Cart cart = new Cart(user);
        cartRepository.save(cart);

        Order order = new Order(cart, user);
        //when

        orderRepository.save(order);
        orderRepository.delete(order);

        Long id = order.getId();
        Optional<Order> orderOptional = orderRepository.findById(id);
        //then
        assertFalse(orderOptional.isPresent());
    }
    @Test
    void shouldUpdateOrderWithNewCartItem(){
        //given
        User user = new User("Marcin","Pajak","test@test.com","pele","password","accessKey");
        userRepository.save(user);
        Cart cart = new Cart(user);
        cartRepository.save(cart);
        Order order = new Order( cart, user);
        orderRepository.save(order);

        Product product = new Product("hammer","sth to hammer",new BigDecimal(15),14,null);
       productRepository.save(product);
        CartItem cartItem = new CartItem(cart,15,product);
        cartItemRepository.save(cartItem);
        cart.addCartItem(cartItem);

        cartRepository.save(cart);
        orderRepository.save(order);
        //when
        Long id = order.getId();
        Optional<Order> orderOptional = orderRepository.findById(id);
        assertEquals(product.getName(),orderOptional.get().getCart().getCartItems().getFirst().getProduct().getName());


    }

}