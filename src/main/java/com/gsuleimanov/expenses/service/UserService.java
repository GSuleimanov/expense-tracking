package com.gsuleimanov.expenses.service;

import com.gsuleimanov.expenses.model.User;
import com.gsuleimanov.expenses.model.UserRegistrationRequest;

public interface UserService {
    User registerUser(UserRegistrationRequest userRegistrationRequest);
}