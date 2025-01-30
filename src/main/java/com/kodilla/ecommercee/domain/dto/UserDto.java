package com.kodilla.ecommercee.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private String password;
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private String accessKey;
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private LocalDateTime accessKeyExpirationTime;
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private boolean isBlocked;
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private List<CartDto> carts;
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private List<OrderDto> orders;
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private LocalDateTime createdAt;

    public UserDto(String firstName, String lastName, String email, String username, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.username = username;
        this.password = password;
    }
}
