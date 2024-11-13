package com.kodilla.ecommercee.mapper;


import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.CartItem;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.domain.dto.CartItemDto;
import com.kodilla.ecommercee.repository.CartItemRepository;
import com.kodilla.ecommercee.repository.CartRepository;
import com.kodilla.ecommercee.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartItemMapper {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;

    public CartItemDto mapToCartItemDto(CartItem cartItem) {
        return new CartItemDto(cartItem.getId(),
               cartItem.getCart().getId(), cartItem.getProduct().getId(),cartItem.getQuantity());
    }
    public CartItem mapToCartItem(CartItemDto cartItemDto) {

        if(cartItemDto.getCartId()==null || cartItemDto.getProductId()==null){
            throw new IllegalArgumentException("Invalid cartItemDto");
        }
        Cart cart = cartRepository.findById(cartItemDto.getCartId()).orElseThrow(()->new IllegalArgumentException("Invalid cartId" + cartItemDto.getCartId()));
        Product product = productRepository.findById(cartItemDto.getProductId()).orElseThrow(()->new IllegalArgumentException("Invalid productId" + cartItemDto.getProductId()));
        return new CartItem(cartItemDto.getId(),cart,cartItemDto.getQuantity(),product);
    }


}
