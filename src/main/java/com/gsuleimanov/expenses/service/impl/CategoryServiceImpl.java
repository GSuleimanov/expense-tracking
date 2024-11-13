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
        return categoryRepository.findAll().stream()
                .map(categoryConverter::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public Category createCategory(CategoryRequest categoryRequest) {
        var category = CategoryDao.builder()
                .name(categoryRequest.getName())
                .description(categoryRequest.getDescription())
                .build();

        var savedCategory = categoryRepository.save(category);
        return categoryConverter.toDto(savedCategory);
    }
}