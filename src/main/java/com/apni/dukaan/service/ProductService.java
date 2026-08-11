package com.apni.dukaan.service;

import com.apni.dukaan.dto.request.ProductRequest;
import com.apni.dukaan.dto.response.ProductResponse;

import java.util.List;

public interface ProductService {

    ProductResponse createProduct(ProductRequest request);
    List<ProductResponse> getAllProducts();
    ProductResponse getProductById(Long id);
    ProductResponse updateProduct(Long id, ProductRequest request);
    void deleteProduct(Long id);
}