package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.domain.dto.LoginRequest;
import com.kodilla.ecommercee.exception.UserNotFoundException;
import com.kodilla.ecommercee.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public String generateKey(String username) throws UserNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(UserNotFoundException.BY_USERNAME_MESSAGE));
        String accessKey = UUID.randomUUID().toString();
        user.setAccessKey(accessKey);
        user.setAccessKeyExpirationTime(LocalDateTime.now().plusHours(1).truncatedTo(ChronoUnit.SECONDS));
        userRepository.save(user);
        return accessKey;
    }

    public boolean authenticateLogin(LoginRequest loginRequest) throws UserNotFoundException {
        User user = userRepository.findByUsername(loginRequest.getUsername())
                .orElseThrow(() -> new UserNotFoundException(UserNotFoundException.BY_USERNAME_MESSAGE));
        return passwordEncoder.matches(loginRequest.getPassword(), user.getPassword());
    }

    public String hashPassword(String password) {
        return passwordEncoder.encode(password);
    }
}
