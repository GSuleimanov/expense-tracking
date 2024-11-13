package com.gsuleimanov.expenses.service.impl;

import com.gsuleimanov.expenses.converter.CategoryConverter;
import com.gsuleimanov.expenses.entity.CategoryDao;
import com.gsuleimanov.expenses.model.Category;
import com.gsuleimanov.expenses.model.CategoryRequest;
import com.gsuleimanov.expenses.repository.CategoryRepository;
import com.gsuleimanov.expenses.repository.UserRepository;
import com.gsuleimanov.expenses.service.CategoryService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryConverter categoryConverter;
    private final UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Category> findAllCategories() {
        // TODO: Implement user-specific categories when authentication is added
        var categories = categoryRepository.findAll();
        return categories.stream()
                .map(categoryConverter::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public Category createCategory(CategoryRequest categoryRequest) {
        // TODO: Replace with actual authenticated user when security is implemented
        var user = userRepository.findById(1L)
                .orElseThrow(() -> new RuntimeException("Default user not found"));

        var category = CategoryDao.builder()
                .name(categoryRequest.getName())
                .description(categoryRequest.getDescription())
                .user(user)
                .build();

        var savedCategory = categoryRepository.save(category);
        return categoryConverter.toDto(savedCategory);
    }
}