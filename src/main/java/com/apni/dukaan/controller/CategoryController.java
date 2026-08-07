package com.apni.dukaan.controller;

import com.apni.dukaan.dto.request.CategoryRequest;
import com.apni.dukaan.dto.response.CategoryResponse;
import com.apni.dukaan.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/category")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping
    public CategoryResponse createCategory(
            @RequestBody CategoryRequest request){

        return categoryService.createCategory(request);
    }
    @GetMapping
    public List<CategoryResponse> getAllCategories() {
        return categoryService.getAllCategories();
    }
}
