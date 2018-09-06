package com.myretail.controller;

import com.mongodb.MongoException;
import com.myretail.model.Product;
import com.myretail.service.ProductSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final Logger log = Logger.getLogger(ProductController.class.getName());

    @Autowired
    ProductSearchService productSearchService;

    @GetMapping(path = "/{productId}")
    public ResponseEntity<Product> getProductDetails(@PathVariable int productId) throws HttpClientErrorException, MongoException, IOException {

        Optional<Product> product = productSearchService.getProductById(productId);

        if (product.isPresent()) {

            return new ResponseEntity<>(product.get(), HttpStatus.OK);

        } else {

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }


    }

    @PutMapping(path = "/{productId}")
    public ResponseEntity<Product> update(@PathVariable int productId, @RequestBody Product product) {

        return new ResponseEntity<>(productSearchService.update(productId, product), HttpStatus.ACCEPTED);

    }

    @GetMapping(path = "")
    public ResponseEntity<List<Product>> getAllProducts() {

        return new ResponseEntity<>(productSearchService.getAllProducts(), HttpStatus.OK);

    }

    @PostMapping()
    public ResponseEntity<Product> create(@RequestBody Product product) {

        return new ResponseEntity<>(productSearchService.create(product), HttpStatus.CREATED);

    }


}
