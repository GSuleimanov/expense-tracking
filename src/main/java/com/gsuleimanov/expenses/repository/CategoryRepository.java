package com.gsuleimanov.expenses.repository;

import com.gsuleimanov.expenses.model.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
    // Add any specific query methods if needed
}
