package com.example.test.service;

import com.example.test.data.dto.ProductDto;

public interface ProductService {
    ProductDto saveProduct(String productId, String ProductName, int productPrice, int productStock);

    ProductDto getProduct(String productId);

}
