package com.ecommerce.controller;

import com.ecommerce.entity.Product;
import com.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * ExternalEcomController provides fast read operation for the
 * shoppers’ personalized information.
 *
 * @author Veni T
 */
@RestController
@RequestMapping("/external")
public class ExternalEcomController {

    @Autowired
    private ProductService productService;

    @GetMapping(value = "/products", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Product>> getProducts(@RequestParam(name = "shopperId") String shopperId,
                                                     @RequestParam(name = "category", required = false) String category,
                                                     @RequestParam(name = "brand", required = false) String brand,
                                                     @RequestParam(name = "limit", defaultValue = "10") int limit) {
        List<Product> productList = productService.getProductsByFilter(shopperId, category, brand, limit);
        return productList.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(productList);
    }
}