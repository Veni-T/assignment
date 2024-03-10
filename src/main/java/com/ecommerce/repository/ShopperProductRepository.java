package com.ecommerce.repository;

import com.ecommerce.entity.ShopperProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShopperProductRepository extends JpaRepository<ShopperProduct,String> {

    List<ShopperProduct> findByShopperId(String shopperId);

}

