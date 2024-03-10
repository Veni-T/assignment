package com.ecommerce.service;

import com.ecommerce.entity.Product;
import com.ecommerce.entity.Shopper;
import com.ecommerce.entity.ShopperProduct;
import com.ecommerce.model.ShopperData;
import com.ecommerce.repository.ProductRepository;
import com.ecommerce.repository.ShopperProductRepository;
import com.ecommerce.repository.ShopperRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class InternalDataServiceTest {

    @Mock
    private ShopperRepository shopperRepository;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ShopperProductRepository shopperProductRepository;

    @InjectMocks
    private InternalDataService internalDataService;

    @Test
    public void testSaveShopperDetails_NewShopper() {
        ShopperData shopperData = getShopperData();

        // Mock behavior of repositories
        when(shopperRepository.getShopperByShopperId("123")).thenReturn(Shopper.builder().build());

        // Calling the service method
        internalDataService.saveShopperDetails(shopperData);

        shopperRepository.save(Shopper.builder().build());
        // Verify that the shopper is saved
        verify(shopperRepository).save(Shopper.builder().build());

    }

    private static ShopperData getShopperData() {
        List<Product> productList = new ArrayList<>();
        productList.add(new Product("1", "Product 1", "Electronics", 45.34567));
        productList.add(new Product("2", "Product 2", "Electronics", 78.3456789));
        return ShopperData.builder().shopperId("123").shelf(productList).build();
    }


    @Test
    public void testInsertShopperProduct() {
        // Mocking data
        ShopperData shopperData = getShopperData();

        // Mock behavior of repositories
        when(productRepository.findByProductId("456")).thenReturn(Optional.empty());

        // Calling the private method
        internalDataService.insertShopperProduct(shopperData);

        productRepository.save(Product.builder().build());

        // Verify that the product is saved
        verify(productRepository).save(Product.builder().build());

        shopperProductRepository.save(ShopperProduct.builder().build());
        // Verify that shopperProduct is saved
        verify(shopperProductRepository).save(ShopperProduct.builder().build());
    }
}
