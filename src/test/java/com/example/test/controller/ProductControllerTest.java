package com.example.test.controller;

import com.example.test.data.dto.ProductDto;
import com.example.test.service.impl.ProductServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    ProductServiceImpl productService;

    @Test
    @DisplayName("Product 데이터 가져오기 테스트")
    void getProductTest() throws Exception {
        given(productService.getProduct("12315")).willReturn(
            new ProductDto("15871", "pen", 5000, 2000));

        String productId = "12315";

        mockMvc.perform(
                        get("/api/v1/product-api/product/" + productId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productId").exists())
                .andExpect(jsonPath("$.productName").exists())
                .andExpect(jsonPath("$.productPrice").exists())
                .andExpect(jsonPath("$.productStock").exists())
                .andDo(print());


        verify(productService).getProduct("12315");
    }

    @Test
    @DisplayName("Product 데이터 생성 테스트")
    void createProductTest() throws Exception {

        given(productService.saveProduct("15871", "pen", 5000, 2000)).willReturn(
                new ProductDto("15871", "pen", 5000, 2000)
        );

        ProductDto productDto = ProductDto.builder().productId("15871").productName("pen").productPrice(5000).productStock(2000).build();

        Gson gson = new Gson();

        String content = gson.toJson(productDto);

        String json = new ObjectMapper().writeValueAsString(productDto);

        mockMvc.perform(
                post("/api/v1/product-api/product")
                        .content(content)
                        .contentType(MediaType.APPLICATION_JSON)
                )

                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productId").exists())
                .andExpect(jsonPath("$.productName").exists())
                .andExpect(jsonPath("$.productPrice").exists())
                .andExpect(jsonPath("$.productStock").exists())
                .andDo(print());

        verify(productService).saveProduct("15871", "pen", 5000, 2000);
    }
}
