package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.dto.LoginRequest;
import com.kodilla.ecommercee.domain.dto.UserDto;
import com.kodilla.ecommercee.exception.UserNotFoundException;
import com.kodilla.ecommercee.service.AuthenticationService;
import com.kodilla.ecommercee.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/users")
public class UserController {

    private final UserService userService;
    private final AuthenticationService authenticationService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> addUser(@RequestBody UserDto userDto) {
        userService.addUser(userDto);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/block/{userId}")
    public ResponseEntity<UserDto> blockUser(@PathVariable Long userId) throws UserNotFoundException {
        return ResponseEntity.ok(userService.blockUser(userId));
    }

    @GetMapping("/generate-key")
    public ResponseEntity<String> generateKey(@RequestBody LoginRequest loginRequest) throws UserNotFoundException {
        boolean isAuthenticated = authenticationService.authenticateLogin(loginRequest);
        if (!isAuthenticated) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Wrong username or password");
        }
        return ResponseEntity.ok(authenticationService.generateKey(loginRequest.getUsername()));
    }
}