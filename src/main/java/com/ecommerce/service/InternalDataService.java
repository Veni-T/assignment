package com.ecommerce.service;

import com.ecommerce.model.ShopperData;
import com.ecommerce.entity.Product;
import com.ecommerce.entity.Shopper;
import com.ecommerce.entity.ShopperProduct;
import com.ecommerce.repository.ProductRepository;
import com.ecommerce.repository.ShopperProductRepository;
import com.ecommerce.repository.ShopperRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@Slf4j
public class InternalDataService {

    @Autowired
    private ShopperRepository shopperRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ShopperProductRepository shopperProductRepository;


    @Transactional
    public void saveShopperDetails(ShopperData shopperData) {
        try{
            if(Objects.isNull(shopperRepository.getShopperByShopperId(shopperData.getShopperId()))){
                shopperRepository.save(Shopper.builder().shopperId(shopperData.getShopperId()).build());
            }
            insertShopperProduct(shopperData);
        }catch (Exception exception){
            log.info("Exception occurred while inserting the record: " +exception.getMessage());
        }
    }

    public void insertShopperProduct(ShopperData shopperData) {
        try{
            if(!shopperData.getShelf().isEmpty()){
                shopperData.getShelf().forEach(product -> {
                    String productId=product.getProductId();
                    if(productRepository.findByProductId(productId).isEmpty()){
                        productRepository.save(Product.builder().productId(productId).relevancyStore(product.getRelevancyStore()).build());
                    }
                    ShopperProduct shopperProduct=ShopperProduct.builder().shopperId(shopperData.getShopperId()).productId(productId).build();
                    shopperProductRepository.save(shopperProduct);
                });
            }
        }catch (Exception exception){
            log.info("Exception occurred while inserting the record: " +exception.getMessage() );
        }
    }

}
