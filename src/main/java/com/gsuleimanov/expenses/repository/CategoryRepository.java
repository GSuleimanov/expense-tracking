package com.gsuleimanov.expenses.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gsuleimanov.expenses.entity.CategoryDao;

public interface CategoryRepository extends JpaRepository<CategoryDao, Long> {
}
