package com.gsuleimanov.expenses.controller;

import com.gsuleimanov.expenses.api.CategoriesApi;
import com.gsuleimanov.expenses.model.Category;
import com.gsuleimanov.expenses.model.CategoryRequest;
import com.gsuleimanov.expenses.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CategoriesController implements CategoriesApi {
    private final CategoryService categoryService;

    @Override
    public ResponseEntity<List<Category>> listCategories() {
        var categories = categoryService.findAllCategories();
        return ResponseEntity.ok(categories);
    }

    @Override
    public ResponseEntity<Category> createCategory(CategoryRequest categoryRequest) {
        var createdCategory = categoryService.createCategory(categoryRequest);
        return ResponseEntity.status(201).body(createdCategory);
    }
}