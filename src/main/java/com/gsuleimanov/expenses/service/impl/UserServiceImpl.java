package com.gsuleimanov.expenses.service.impl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gsuleimanov.expenses.converter.UserConverter;
import com.gsuleimanov.expenses.entity.UserDao;
import com.gsuleimanov.expenses.exception.UserAlreadyExistsException;
import com.gsuleimanov.expenses.model.User;
import com.gsuleimanov.expenses.model.UserRegistrationRequest;
import com.gsuleimanov.expenses.repository.UserRepository;
import com.gsuleimanov.expenses.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserConverter userConverter;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public User registerUser(UserRegistrationRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new UserAlreadyExistsException("Username already exists: " + request.getUsername());
        }

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new UserAlreadyExistsException("Email already exists: " + request.getEmail());
        }

        var encodedPassword = passwordEncoder.encode(request.getPassword());

        var user = UserDao.builder()
            .username(request.getUsername())
            .email(request.getEmail())
            .passwordHash(encodedPassword)
            .build();

        var savedUser = userRepository.save(user);

        return userConverter.toDto(savedUser);
    }
}