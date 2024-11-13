package com.gsuleimanov.expenses.service.impl;

import com.gsuleimanov.expenses.converter.UserConverter;
import com.gsuleimanov.expenses.entity.UserDao;
import com.gsuleimanov.expenses.exception.UserAlreadyExistsException;
import com.gsuleimanov.expenses.model.User;
import com.gsuleimanov.expenses.model.UserRegistrationRequest;
import com.gsuleimanov.expenses.repository.UserRepository;
import com.gsuleimanov.expenses.service.UserService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserConverter userConverter;
    // private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public User registerUser(UserRegistrationRequest request) {
        // Check if user already exists
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new UserAlreadyExistsException("Username already exists: " + request.getUsername());
        }

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new UserAlreadyExistsException("Email already exists: " + request.getEmail());
        }

        // Create new user
        var user = UserDao.builder()
            .username(request.getUsername())
            .email(request.getEmail())
            .build();

        // Encode password before storing
        // user.setPasswordHash(passwordEncoder.encode(request.getPassword()));

        // Save user
        UserDao savedUser = userRepository.save(user);

        // Convert and return
        return userConverter.toDto(savedUser);
    }
}