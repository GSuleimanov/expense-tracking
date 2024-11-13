package com.gsuleimanov.expenses.service;

import com.gsuleimanov.expenses.model.Category;
import com.gsuleimanov.expenses.model.CategoryRequest;

import java.util.List;

public interface CategoryService {
    List<Category> findAllCategories();
    Category createCategory(CategoryRequest categoryRequest);
} 