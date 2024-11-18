package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.domain.dto.UserDto;
import com.kodilla.ecommercee.exception.UserNotFoundException;
import com.kodilla.ecommercee.mapper.UserMapper;
import com.kodilla.ecommercee.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final AuthenticationService authenticationService;

    public void addUser(UserDto userDto) {
        String hashedPassword = authenticationService.hashPassword(userDto.getPassword());
        userDto.setPassword(hashedPassword);
        userRepository.save(userMapper.mapToUserEntity(userDto));
    }

    public UserDto blockUser(Long userId) throws UserNotFoundException {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(UserNotFoundException.BY_ID_MESSAGE));
        if (user.isBlocked()) {
            throw new UserNotFoundException(UserNotFoundException.IS_BLOCKED_MESSAGE);
        }
        user.setBlocked(true);
        return userMapper.mapToUserDto(userRepository.save(user));
    }
}
