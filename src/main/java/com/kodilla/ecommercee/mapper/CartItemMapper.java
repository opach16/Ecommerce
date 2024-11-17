package com.kodilla.ecommercee.mapper;


import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.CartItem;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.domain.dto.CartItemDto;
import com.kodilla.ecommercee.exception.CartNotFoundException;
import com.kodilla.ecommercee.exception.ProductNotFoundException;
import com.kodilla.ecommercee.repository.CartItemRepository;
import com.kodilla.ecommercee.repository.CartRepository;
import com.kodilla.ecommercee.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CartItemMapper {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;

    public CartItemDto mapToCartItemDto(CartItem cartItem) {
        return new CartItemDto(cartItem.getId(),
               cartItem.getCart().getId(), cartItem.getProduct().getId(),cartItem.getQuantity());
    }
    public CartItem mapToCartItem(CartItemDto cartItemDto) throws CartNotFoundException,ProductNotFoundException {

        if(cartItemDto.getCartId()==null || cartItemDto.getProductId()==null){
            throw new IllegalArgumentException("Invalid cartItemDto");
        }
        Cart cart = cartRepository.findById(cartItemDto.getCartId()).orElseThrow(CartNotFoundException::new);
        Product product = productRepository.findById(cartItemDto.getProductId()).orElseThrow(ProductNotFoundException::new);
        return new CartItem(cartItemDto.getId(),cart,cartItemDto.getQuantity(),product);
    }

    public List<CartItemDto> mapToCartItemDtoList(List<CartItem> cartItemList) {
        return cartItemList.stream()
                .map(this::mapToCartItemDto)
                .toList();
    }
}
