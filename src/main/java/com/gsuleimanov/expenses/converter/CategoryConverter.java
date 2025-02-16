package com.gsuleimanov.expenses.converter;

import com.gsuleimanov.expenses.model.CategoryEntity;
import com.gsuleimanov.expenses.model.Category;
import com.gsuleimanov.expenses.model.CategoryRequest;
import org.springframework.stereotype.Component;

@Component
public class CategoryConverter {

    public Category toDto(CategoryEntity record) {
        if (record == null) return null;

        return new Category()
                .id(record.getId())
                .name(record.getName())
                .description(record.getDescription())
                .createdAt(record.getCreatedAt());
    }

    public CategoryEntity toRecord(CategoryRequest request) {
        if (request == null) return null;

        return CategoryEntity.builder()
                .name(request.getName())
                .description(request.getDescription())
                .build();
    }
}