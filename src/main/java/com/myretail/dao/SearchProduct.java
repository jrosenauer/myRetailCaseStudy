package com.myretail.dao;

import com.myretail.model.CurrentPrice;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SearchProduct extends MongoRepository<CurrentPrice, Integer> {
    public CurrentPrice findbyProductId(int productId);
}
