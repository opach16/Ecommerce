package com.kodilla.ecommercee.domain.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private String password;
    private String accessKey;
    private LocalDateTime accessKeyExpirationTime;
    private boolean isBlocked;
    private List<CartDto> carts;
    private List<OrderDto> orders;
    private LocalDateTime createdAt;

    public UserDto(String firstName, String lastName, String email, String username, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.username = username;
        this.password = password;
    }
}
