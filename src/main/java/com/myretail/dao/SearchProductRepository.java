package com.myretail.dao;

import com.myretail.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface SearchProductRepository extends MongoRepository<Product, Integer> {

    public Optional<Product> getByProductId(int productId);

}
