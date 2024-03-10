package com.ecommerce.service;

import com.ecommerce.entity.Product;
import com.ecommerce.entity.ShopperProduct;
import com.ecommerce.repository.ProductRepository;
import com.ecommerce.repository.ShopperProductRepository;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ShopperProductRepository shopperProductRepository;

    public List<Product> getProductsByFilter(String shopperId, String category, String brand, int limit) {
        List<ShopperProduct> shopperProducts= new ArrayList<>();
        Pageable pageable= PageRequest.of(0,Math.min(limit,100));
            shopperProducts= shopperProductRepository.findByShopperId(shopperId);
        if(shopperProducts.isEmpty()){
           return new ArrayList<>();
        }else{
            return filterProducts(shopperProducts, category,  brand,  limit)  ;
        }
    }

    public List<Product> filterProducts(List<ShopperProduct> shopperProducts, String category, String brand, int limit) {
        List<Product> productList = new ArrayList<>();
        Pageable pageable=PageRequest.of(0,Math.min(limit,100));
        shopperProducts.forEach(product -> {
            String productId= product.getProductId();
            if(StringUtils.isBlank(category) && StringUtils.isBlank(brand)){
                productRepository.findByProductId(productId,pageable).ifPresent(productList::addAll);
            } else if(StringUtils.isNotBlank(category) && StringUtils.isBlank(brand)){
               productRepository.findByProductIdAndCategory(productId,category,pageable).ifPresent(productList::addAll);
            }else if(StringUtils.isBlank(category) && StringUtils.isNotBlank(brand)){
               productRepository.findByProductIdAndBrand(productId,brand,pageable).ifPresent(productList::addAll);
            }else{
                productRepository.findByProductIdAndCategoryAndBrand(productId,category,brand,pageable).ifPresent(productList::addAll);
           }
        });
       return productList;
    }


}
