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
@Table( schema = "ecom", name = "shopper_details")
public class Shopper {

    @Id
    @Column(name="shopper_id")
    private String shopperId;

    @Column(name="shopper_name")
    private String shopperName;

}
