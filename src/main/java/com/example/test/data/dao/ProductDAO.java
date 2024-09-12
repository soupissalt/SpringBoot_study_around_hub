package com.example.test.data.dao;

import com.example.test.data.entity.ProductEntity;

public interface ProductDAO {

    ProductEntity saveProduct(ProductEntity productEntity);
    ProductEntity getProduct(String productId);
}
