package com.gsuleimanov.expenses.repository;

import com.gsuleimanov.expenses.model.CategoryDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryDao, Long> {
    // Add any specific query methods if needed
}
