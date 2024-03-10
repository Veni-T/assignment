package com.ecommerce.entity;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(schema = "ecom",  name = "shopper_product_details")
public class ShopperProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="product_id")
    private String productId;

    @Column(name="shopper_id")
    private String shopperId;

}
