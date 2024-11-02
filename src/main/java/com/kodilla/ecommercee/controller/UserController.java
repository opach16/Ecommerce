package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.dto.UserDto;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/v1/users")
public class UserController {

    @PostMapping
    public void addUser(@RequestBody UserDto userDto) {

    }

    @PutMapping("/block/{userId}")
    public UserDto blockUser(@PathVariable Long userId) {
        return new UserDto(1L, "name", "lastname", "mail", "username", "pass", "key", true, LocalDateTime.now());
    }

    @GetMapping("/generate-key/{userId}")
    public String generateKey(@PathVariable Long userId) {
        return "testAccessKey_1234!@#";
    }
}
