package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.domain.dto.OrderDto;
import com.kodilla.ecommercee.exception.CartNotFoundException;
import com.kodilla.ecommercee.exception.UserNotFoundException;
import com.kodilla.ecommercee.repository.CartRepository;
import com.kodilla.ecommercee.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderMapper {
    private final UserRepository userRepository;
    CartRepository cartRepository;

    public OrderDto mapToOrderDto(Order order) {
        return new OrderDto(order.getId(),
                order.getStatus(),
                order.getCart().getId(),
                order.getOrderDate(),
                order.getUser().getId());
    }

    public Order mapToOrder(OrderDto orderDto) throws CartNotFoundException {
        return Order.builder()
                .id(orderDto.getId())
                .status(orderDto.getStatus())
                .orderDate(orderDto.getOrderDate())
                .cart(cartRepository.findById(orderDto.getCartId()).orElseThrow(CartNotFoundException::new))
                .user(userRepository.findById(orderDto.getUserId()).orElseThrow())
                .build();
    }
    public List<OrderDto> mapToOrderDtoList(List<Order> orders) {
        return orders.stream()
                .map(this::mapToOrderDto)
                .toList();
    }


}
