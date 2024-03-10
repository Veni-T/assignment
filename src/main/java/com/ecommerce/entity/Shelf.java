package com.ecommerce.entity;


import jakarta.persistence.Column;
import lombok.Data;

@Data
public  class Shelf {
    @Column(name = "productId")
    private String productId;
    @Column(name = "relevancyScore")
    private String relevancyScore;
}