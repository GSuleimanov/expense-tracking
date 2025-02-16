package com.gsuleimanov.expenses.converter;

import com.gsuleimanov.expenses.model.UserEntity;
import com.gsuleimanov.expenses.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {

    public User toDto(UserEntity record) {
        if (record == null) return null;

        return new User()
                .id(record.getId())
                .username(record.getUsername())
                .email(record.getEmail())
                .createdAt(record.getCreatedAt())
                .updatedAt(record.getUpdatedAt());
    }
}