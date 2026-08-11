package com.apni.dukaan.service;

import com.apni.dukaan.dto.request.ProductRequest;
import com.apni.dukaan.dto.response.ProductResponse;
import com.apni.dukaan.entity.Category;
import com.apni.dukaan.entity.Product;
import com.apni.dukaan.exception.CategoryNotFoundException;
import com.apni.dukaan.exception.ProductAlreadyExistsException;
import com.apni.dukaan.exception.ProductNotFoundException;
import com.apni.dukaan.repository.CategoryRepository;
import com.apni.dukaan.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public ProductResponse createProduct(ProductRequest request) {

        if (productRepository.existsByName(request.getName())) {
            throw new ProductAlreadyExistsException("Product already exists");
        }

        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() ->
                        new CategoryNotFoundException(
                                "Category not found with id: " + request.getCategoryId()
                        )
                );

        Product product = Product.builder()
                .name(request.getName())
                .description(request.getDescription())
                .price(request.getPrice())
                .stockQuantity(request.getStockQuantity())
                .category(category)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        Product savedProduct = productRepository.save(product);

        return mapToResponse(savedProduct);
    }
    @Override
    public List<ProductResponse> getAllProducts() {

        return productRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }
    @Override
    public ProductResponse getProductById(Long id) {

        Product product = productRepository.findById(id)
                .orElseThrow(() ->
                        new ProductNotFoundException(
                                "Product not found with id: " + id
                        )
                );

        return mapToResponse(product);
    }
    @Override
    public ProductResponse updateProduct(Long id, ProductRequest request) {

        Product product = productRepository.findById(id)
                .orElseThrow(() ->
                        new ProductNotFoundException(
                                "Product not found with id: " + id
                        )
                );

        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() ->
                        new CategoryNotFoundException(
                                "Category not found with id: " + request.getCategoryId()
                        )
                );

        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setStockQuantity(request.getStockQuantity());
        product.setCategory(category);
        product.setUpdatedAt(LocalDateTime.now());

        Product updatedProduct = productRepository.save(product);

        return mapToResponse(updatedProduct);
    }
    @Override
    public void deleteProduct(Long id) {

        Product product = productRepository.findById(id)
                .orElseThrow(() ->
                        new ProductNotFoundException(
                                "Product not found with id: " + id
                        )
                );

        productRepository.delete(product);
    }
    private ProductResponse mapToResponse(Product product) {

        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .stockQuantity(product.getStockQuantity())
                .categoryId(product.getCategory().getId())
                .categoryName(product.getCategory().getName())
                .createdAt(product.getCreatedAt())
                .updatedAt(product.getUpdatedAt())
                .build();
    }
}