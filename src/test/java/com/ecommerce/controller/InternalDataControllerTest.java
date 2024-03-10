package com.ecommerce.controller;


import com.ecommerce.entity.Product;
import com.ecommerce.model.ShopperData;
import com.ecommerce.service.InternalDataService;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class InternalDataControllerTest {

    @Mock
    private InternalDataService internalDataService;

    @InjectMocks
    private InternalDataController internalDataController;


    @Test
    public void saveShoppersInfoTest(){
        List<Product> productList = new ArrayList<>();
        productList.add(Product.builder().productId("1").category("Electronics").brand("Sony").relevancyStore(45.34567).build());
        productList.add(Product.builder().productId("2").category("Electronics").brand("Samsung").relevancyStore(45.34567).build());

        ShopperData shopperData=ShopperData.builder().shopperId("S-0001").shelf(productList).build();

        doNothing().when(internalDataService).saveShopperDetails(any(ShopperData.class));

        // Calling the controller method
        ResponseEntity<String> responseEntity = internalDataController.saveShoppersInfo(shopperData);

        // Verifying that the service method was called with the correct parameter
        verify(internalDataService).saveShopperDetails(shopperData);

        // Asserting the response
        ResponseEntity<String> expectedResponse = ResponseEntity.status(HttpStatus.CREATED).body("Data Saved Successfully");
        assertEquals(expectedResponse, responseEntity);



    }
}
