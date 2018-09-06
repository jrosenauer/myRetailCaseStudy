package com.myretail.service;

import com.mongodb.MongoException;
import com.myretail.model.CurrentPrice;
import com.myretail.model.Product;
import org.omg.CORBA.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.io.IOException;
import java.util.logging.Logger;

@Service(value = "productSearchService")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ProductSearchServiceImpl implements ProductSearchService {
    private final Logger log = Logger.getLogger(ProductSearchServiceImpl.class.getName());

    @Autowired
    CurrentPrice currentPrice;
    @Autowired
    ProductSearchServiceImpl productSearchServiceImpl;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private Environment environment;

    @Override
    public Product getProductSearch(int productId) throws MongoException, IOException {
        log.info("in getProductSearch");
        log.debug("id: " + productId);
        String name = getName(productId);
        log.debug("productName: " + name);
        CurrentPrice value = productSearchServiceImpl.getProductSearch(productId);
        if (name == null) {
            log.error("price detail null mongo exception thrown");
            throw new MongoException("price details for the product with id " + productId + " were not found in mongo db.");
        }
        Product product = new Product(productId, name, value);
        log.debug("Product Details: " + product);
        return product;
    }

}
