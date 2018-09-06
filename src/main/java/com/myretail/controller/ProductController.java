package com.myretail.controller;

import com.mongodb.MongoException;
import com.myretail.model.Product;
import com.myretail.service.ProductSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import java.io.IOException;
import java.util.logging.Logger;

@RestController
@RequestMapping("/")
public class ProductController {

    private final Logger log = Logger.getLogger(ProductController.class.getName());

    @Autowired
    @Qualifier(value = "productSearchService")
    ProductSearchService productSearchService;

    @RequestMapping(value = "/product/{id}", method = RequestMethod.GET)
    public Product getProductDetails(@PathVariable int productId) throws HttpClientErrorException, MongoException, IOException {
        log.info("in controller getProductDetails id : " + productId);
        //Product product = null;
        Product product = productSearchService.getProductSearch(productId);
        log.info(" return productDetails : " + product);
        return product;

    }
}
