package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.CartItem;
import com.kodilla.ecommercee.domain.dto.CartDto;
import com.kodilla.ecommercee.domain.dto.CartItemDto;
import com.kodilla.ecommercee.exception.CartNotFoundException;
import com.kodilla.ecommercee.mapper.CartItemMapper;
import com.kodilla.ecommercee.mapper.CartMapper;
import com.kodilla.ecommercee.repository.CartItemRepository;
import com.kodilla.ecommercee.repository.CartRepository;
import com.kodilla.ecommercee.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;
    private final CartMapper cartMapper;
    private final CartItemRepository cartItemRepository;
    private final CartItemMapper cartItemMapper;



    public void createCart(CartDto cartDto) {
        Cart cart = cartMapper.mapToCart(cartDto);
        cartRepository.save(cart);
    }

    public List<CartItemDto> getAllCartItems(Long cartId) {
        List<CartItem> cartItemList = cartItemRepository.findCartItemByCartId(cartId);

        return cartItemMapper.mapToCartItemDtoList(cartItemList);
    }

    public void addCartItem(Long cartId, CartItemDto cartItemDto) throws CartNotFoundException {
        Cart cart = cartRepository.findById(cartId).orElseThrow(CartNotFoundException::new);
        CartItem cartItem = cartItemMapper.mapToCartItem(cartItemDto);
        cart.addCartItem(cartItem);
        cartRepository.save(cart);
    }

    public void deleteCartItemById(Long cartItemId) {
        cartItemRepository.deleteById(cartItemId);
    }
}
