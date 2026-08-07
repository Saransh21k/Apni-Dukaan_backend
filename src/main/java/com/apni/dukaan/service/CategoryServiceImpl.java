package com.apni.dukaan.service;

import com.apni.dukaan.dto.request.CategoryRequest;
import com.apni.dukaan.dto.response.CategoryResponse;
import com.apni.dukaan.entity.Category;
import com.apni.dukaan.exception.CategoryAlreadyExistsException;
import com.apni.dukaan.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public CategoryResponse createCategory(CategoryRequest request) {

        if (categoryRepository.existsByName(request.getName())) {
            throw new CategoryAlreadyExistsException("Category already exists");
        }

        Category category = Category.builder()
                .name(request.getName())
                .description(request.getDescription())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        Category savedCategory = categoryRepository.save(category);

        return mapToResponse(savedCategory);
    }

    @Override
    public List<CategoryResponse> getAllCategories() {

        List<Category> categories = categoryRepository.findAll();

        return categories.stream()
                .map(this::mapToResponse)
                .toList();
    }

    private CategoryResponse mapToResponse(Category category) {

        return CategoryResponse.builder()
                .id(category.getId())
                .name(category.getName())
                .description(category.getDescription())
                .createdAt(category.getCreatedAt())
                .updatedAt(category.getUpdatedAt())
                .build();
    }
}