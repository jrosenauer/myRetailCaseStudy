package com.myretail.service;

import com.mongodb.MongoException;
import com.myretail.dao.SearchProductRepository;
import com.myretail.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class ProductSearchService {

    private SearchProductRepository searchProductRepository;

    @Autowired
    public ProductSearchService(final SearchProductRepository searchProductRepository) {

        this.searchProductRepository = searchProductRepository;

    }

    public Optional<Product> getProductById(int productId) throws MongoException, HttpClientErrorException, IOException {

        return searchProductRepository.getByProductId(productId);

    }

    public List<Product> getAllProducts() {

        return searchProductRepository.findAll();

    }

    public Product update(int productId, Product product) {

        product.setProductId(productId);

        return searchProductRepository.save(product);

    }

    public Product create(Product product) {

        return searchProductRepository.save(product);

    }


}
