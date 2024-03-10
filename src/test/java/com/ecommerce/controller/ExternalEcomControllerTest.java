package com.ecommerce.controller;

import com.ecommerce.entity.Product;
import com.ecommerce.service.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ExternalEcomControllerTest {

    @Mock
    private ProductService productService;

    @InjectMocks
    private ExternalEcomController externalEcomController;

    @Test
    public void testGetProducts() {
        String shopperId = "123";
        String category = "Electronics";
        String brand = "Samsung";
        int limit = 10;

        List<Product> productList = new ArrayList<>();
        productList.add(Product.builder().productId("1").category("Electronics").brand("Sony").relevancyStore(45.34567).build());
        productList.add(Product.builder().productId("2").category("Electronics").brand("Samsung").relevancyStore(45.34567).build());


        // Mocking ProductService behavior
        when(productService.getProductsByFilter(shopperId, category, brand, limit)).thenReturn(productList);

        // Calling the controller method
        ResponseEntity<List<Product>> responseEntity = externalEcomController.getProducts(shopperId, category, brand, limit);

        // Asserting the response
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(productList, responseEntity.getBody());
    }
}
