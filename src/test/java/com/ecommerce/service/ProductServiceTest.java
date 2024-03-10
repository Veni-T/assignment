package com.ecommerce.service;

import com.ecommerce.entity.Product;
import com.ecommerce.repository.ShopperProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {


    @Mock
    private ShopperProductRepository shopperProductRepository;

    @InjectMocks
    private ProductService productService;

    @Test
    public void testGetProductsByFilter() {
        // Mocking data
        String shopperId = "S-0001";
        String category = "Electronics";
        String brand = "Sony";
        int limit = 10;

        List<Product> productList = new ArrayList<>();
        productList.add( Product.builder().productId("1").category("Electronics").brand("Sony").relevancyStore(45.2345678).build());

        // Mocking behavior of shopperProductRepository
        when(shopperProductRepository.findByShopperId(shopperId)).thenReturn(new ArrayList<>());


        // Calling the service method
        List<Product> result = productService.getProductsByFilter(shopperId, category, brand, limit);

        // Asserting the result
        assertNotEquals(productList, result);
    }

    }
