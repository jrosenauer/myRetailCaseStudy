package com.myretail.service;

import com.mongodb.MongoException;
import com.myretail.model.Product;
import org.springframework.web.client.HttpClientErrorException;

import java.io.IOException;

public interface ProductSearchService {

    public Product getProductSearch(int productId) throws MongoException, HttpClientErrorException, IOException;

}
