package com.myretail.model;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class Product {

    @Id
    private String id;

    private int productId;

    private String name;

    private CurrentPrice currentPrice;

}
