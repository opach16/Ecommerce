package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.CartItem;
import com.kodilla.ecommercee.domain.dto.CartDto;
import com.kodilla.ecommercee.domain.dto.CartItemDto;
import com.kodilla.ecommercee.exception.CartItemNotFoundException;
import com.kodilla.ecommercee.exception.CartNotFoundException;
import com.kodilla.ecommercee.exception.ProductNotFoundException;
import com.kodilla.ecommercee.mapper.CartItemMapper;
import com.kodilla.ecommercee.mapper.CartMapper;
import com.kodilla.ecommercee.repository.CartItemRepository;
import com.kodilla.ecommercee.repository.CartRepository;
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

    public CartDto createCart(CartDto cartDto) {
        Cart savedCart = cartRepository.save(cartMapper.mapToCart(cartDto));
        return cartMapper.mapToCartDto(savedCart);
    }

    public List<CartItemDto> getAllCartItems(Long cartId) {
        return cartItemMapper.mapToCartItemDtoList(cartItemRepository.findCartItemByCartId(cartId));
    }

    public CartItemDto addCartItem(CartItemDto cartItemDto) throws CartNotFoundException, ProductNotFoundException {
        Cart cart = cartRepository.findById(cartItemDto.getCartId()).orElseThrow(CartNotFoundException::new);
        CartItem cartItem = cartItemMapper.mapToCartItem(cartItemDto);
        cart.addCartItem(cartItem);
        cartRepository.save(cart);
        return cartItemMapper.mapToCartItemDto(cartItem);
    }

    public void deleteCartItemById(Long cartItemId) throws CartItemNotFoundException {
        cartItemRepository.findById(cartItemId).orElseThrow(CartItemNotFoundException::new);
        cartItemRepository.deleteById(cartItemId);
    }
}
