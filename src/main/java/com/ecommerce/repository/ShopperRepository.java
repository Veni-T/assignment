package com.ecommerce.repository;

import com.ecommerce.entity.Shopper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ShopperRepository  extends JpaRepository<Shopper, String> {

    Shopper getShopperByShopperId (String shopperId);

}
