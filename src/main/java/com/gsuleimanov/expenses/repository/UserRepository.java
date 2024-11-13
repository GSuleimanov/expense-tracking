package com.gsuleimanov.expenses.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gsuleimanov.expenses.entity.UserDao;

public interface UserRepository extends JpaRepository<UserDao, Long> {
}
