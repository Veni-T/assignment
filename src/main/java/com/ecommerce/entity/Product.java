package com.ecommerce.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table( schema = "ecom", name="product_details")
public class Product {

    @Id
    @Column(name="product_id")
    private String productId;

    @Column
    private String category;

    @Column
    private String brand;

    @Column(name="relevancy_store")
    private double relevancyStore;


}
