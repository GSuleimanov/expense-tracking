package com.gsuleimanov.expenses.repository;

import com.gsuleimanov.expenses.model.UserDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserDao, Long> {
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}
