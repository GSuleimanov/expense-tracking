package com.gsuleimanov.expenses.controller;

import com.gsuleimanov.expenses.api.UsersApi;
import com.gsuleimanov.expenses.model.User;
import com.gsuleimanov.expenses.model.UserRegistrationRequest;
import com.gsuleimanov.expenses.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UsersController implements UsersApi {
    private final UserService userService;

    @Override
    public ResponseEntity<User> registerUser(UserRegistrationRequest userRegistrationRequest) {
        var registeredUser = userService.registerUser(userRegistrationRequest);
        return ResponseEntity.status(201).body(registeredUser);
    }
}