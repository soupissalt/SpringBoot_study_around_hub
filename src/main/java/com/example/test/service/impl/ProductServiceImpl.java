package com.example.test.service.impl;

import com.example.test.data.dto.ProductDto;
import com.example.test.data.entity.ProductEntity;
import com.example.test.data.handler.ProductDataHandler;
import com.example.test.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
    ProductDataHandler productHandler;

    @Autowired
    public ProductServiceImpl(ProductDataHandler productDataHandler){
        this.productHandler = productDataHandler;
    }

    @Override
    public  ProductDto saveProduct(String productId, String productName, int productPrice, int productStock){
        ProductEntity productEntity = productHandler.saveProductEntity(productId, productName, productPrice, productStock);

        ProductDto productDto = new ProductDto(productEntity.getProductId(), productEntity.getProductName(),
                productEntity.getProductPrice(), productEntity.getProductStock());

        return productDto;
    }

    @Override
    public ProductDto getProduct(String productId){
        ProductEntity productEntity = productHandler.getProductEntity(productId);

        ProductDto productDto = new ProductDto(productEntity.getProductId(), productEntity.getProductName(),
                productEntity.getProductPrice(), productEntity.getProductStock());

        return productDto;
    }
}
