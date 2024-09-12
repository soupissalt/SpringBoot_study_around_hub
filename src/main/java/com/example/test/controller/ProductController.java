package com.example.test.controller;

import com.example.test.common.Constants;
import com.example.test.common.exception.TestException;
import com.example.test.data.dto.ProductDto;
import com.example.test.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/product-api")
public class ProductController {

    private final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);
    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService){
        this.productService = productService;
    }

    @GetMapping(value = "/product/{productId}")
    public ProductDto getProduct(@PathVariable String productId){
        long startTime = System.currentTimeMillis();
        LOGGER.info("[ProductController] perform {} of Test API", "getProduct");

        ProductDto productDto = productService.getProduct(productId);

        LOGGER.info("[ProductController] Response :: productId = {}, productName = {}, productPrice = {}, productStock = {}, Response Time = {}ms",
                productDto.getProductId(),
                productDto.getProductName(),
                productDto.getProductPrice(),
                productDto.getProductStock(),
                System.currentTimeMillis() - startTime);

        return productDto;
    }

    @PostMapping(value = "/product")
    public ResponseEntity<ProductDto> createProduct(@Valid @RequestBody ProductDto productDto){
        if(productDto.getProductId().equals("")||productDto.getProductId().isEmpty()){
            LOGGER.error("[ProductController] falied Response :: productId is empty");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(productDto);
        }
        String productId = productDto.getProductId();
        String productName = productDto.getProductName();
        int productPrice = productDto.getProductPrice();
        int productStock = productDto.getProductStock();

        ProductDto response = productService
                .saveProduct(productId, productName, productPrice, productStock);

        LOGGER.info("[createProduct] Response >> productId : {}, productName : {}, productPrice : {}, productStock : {}",
                response.getProductId(),
                response.getProductName(),
                response.getProductPrice(),
                response.getProductStock());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping(value = "/product/{productId}")
    public ProductDto deleteProduct(@PathVariable String productId){
        return null;
    }

    @PostMapping(value = "/product/exception")
    public void exceptionTest() throws TestException {
        throw new TestException(Constants.ExceptionClass.PRODUCT, HttpStatus.FORBIDDEN, "접근이 금지 되었습니다.");
    }
}
