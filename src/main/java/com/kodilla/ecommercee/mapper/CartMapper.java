package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.CartItem;
import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.domain.dto.CartDto;
import com.kodilla.ecommercee.repository.CartItemRepository;
import com.kodilla.ecommercee.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartMapper {
    private final UserRepository userRepository;
    private final CartItemRepository cartItemRepository;

    public Cart mapToCart(CartDto cartDto) {
        if (cartDto.getUserId() == null) {
            throw new IllegalArgumentException("Invalid user id");
        }
        List<CartItem> cartItemList = cartItemRepository.findCartItemByCartId(cartDto.getId());

        User user = userRepository.findById(cartDto.getUserId()).orElseThrow(() -> new IllegalArgumentException("Invalid user id"));
        return new Cart(
                cartDto.getId(),
                cartItemList,
                cartDto.isOrdered(),
                user,
                cartDto.getTotalPrice()
        );
    }

    public CartDto mapToCartDto(Cart cart) {
        return CartDto.builder()
                .id(cart.getId())
                .userId(cart.getUser().getId())
                .ordered(cart.isOrdered())
                .totalPrice(cart.getTotal())
                .build();
    }
}