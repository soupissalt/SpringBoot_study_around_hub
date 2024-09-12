package com.example.test.service.impl;

import com.example.test.data.dto.ProductDto;
import com.example.test.data.entity.ProductEntity;
import com.example.test.data.handler.impl.ProductDataHandlerImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
@Import({ProductDataHandlerImpl.class, ProductServiceImpl.class})
public class ProductServiceimplTest {
    @MockBean
    ProductDataHandlerImpl productDataHandler;

    @Autowired
    ProductServiceImpl productService;

    @Test
    public void getProductTest(){
        Mockito.when(productDataHandler.getProductEntity("123"))
                .thenReturn(new ProductEntity("123","pen",2000,3000));

        ProductDto productDto = productService.getProduct("123");

        Assertions.assertEquals(productDto.getProductId(),"123");
        Assertions.assertEquals(productDto.getProductName(),"pen");
        Assertions.assertEquals(productDto.getProductPrice(),2000);
        Assertions.assertEquals(productDto.getProductStock(),3000);

        verify(productDataHandler).getProductEntity("123");
    }

    @Test
    public void saveProductTest(){
        Mockito.when(productDataHandler.saveProductEntity("123","pen",2000,3000))
                .thenReturn(new ProductEntity("123","pen",2000,3000));

        ProductDto productDto = productService.saveProduct("123","pen",2000,3000);

        Assertions.assertEquals(productDto.getProductId(),"123");
        Assertions.assertEquals(productDto.getProductName(),"pen");
        Assertions.assertEquals(productDto.getProductPrice(),2000);
        Assertions.assertEquals(productDto.getProductStock(),3000);

        verify(productDataHandler).saveProductEntity("123","pen",2000,3000);
    }
}
