package com.apni.dukaan.service;

import com.apni.dukaan.dto.request.CategoryRequest;
import com.apni.dukaan.dto.response.CategoryResponse;

import java.util.List;

public interface CategoryService {
    CategoryResponse createCategory(CategoryRequest request);
    List<CategoryResponse> getAllCategories();

}
