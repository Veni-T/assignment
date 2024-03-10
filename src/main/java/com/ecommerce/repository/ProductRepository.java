package com.ecommerce.repository;

import com.ecommerce.entity.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository  extends JpaRepository<Product, String> {

    Optional<Product>  findByProductId(String productId);
    Optional<List<Product>>  findByProductId(String productId, Pageable pageable);
    Optional<List<Product>>  findByProductIdAndCategoryAndBrand(String productId,String category, String brand,Pageable pageable);
    Optional<List<Product>>  findByProductIdAndBrand(String productId,String brand,Pageable pageable);
    Optional<List<Product>> findByProductIdAndCategory(String productId, String category,Pageable pageable);


}


