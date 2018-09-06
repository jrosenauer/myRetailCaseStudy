package com.myretail.controllertest;

import com.myretail.model.CurrentPrice;
import com.myretail.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.util.List;
import java.util.Random;

import static org.assertj.core.api.Java6Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductControllerTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private TestRestTemplate restTemplate;

    private Product fixture;

    @Test(priority = 1)
    public void beforeTest() {

        Product product = new Product();
        CurrentPrice currentPrice = new CurrentPrice();
        Random rand = new Random();

        product.setName("Some New Product Fixture");
        product.setProductId(rand.nextInt(999) + 1);
        currentPrice.setValue(BigDecimal.valueOf(19.99));
        currentPrice.setCurrencyCode("eur");
        product.setCurrentPrice(currentPrice);

        ResponseEntity<Product> createdEntity = this.restTemplate.exchange(

                "/products",
                HttpMethod.POST,
                new HttpEntity<>(product),
                new ParameterizedTypeReference<Product>() {
                }

        );

        assertThat(createdEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);

        Product createdProduct = createdEntity.getBody();

        assertThat(createdProduct.getName()).isEqualTo("Some New Product Fixture");

        fixture = createdProduct;

    }

    @Test(priority = 2)
    public void testGetAllProducts() {

        ResponseEntity<List<Product>> entity = this.restTemplate.exchange(

                "/products",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Product>>() {
                }

        );

        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(entity.getBody().size()).isGreaterThanOrEqualTo(1);

    }

    @Test(priority = 3)
    public void testGetProductById() {

        ResponseEntity<Product> entity = this.restTemplate.exchange(

                "/products/" + fixture.getProductId(),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Product>() {
                }

        );

        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);

        Product product = entity.getBody();

        assertThat(product.getProductId()).isEqualTo(fixture.getProductId());

    }

    @Test(priority = 4)
    public void testUpdateProductById() {

        fixture.setName("Some new product name");
        fixture.getCurrentPrice().setValue(BigDecimal.valueOf(12.99));

        ResponseEntity<Product> getEntity = this.restTemplate.exchange(

                "/products/" + fixture.getProductId(),
                HttpMethod.PUT,
                new HttpEntity<>(fixture),
                new ParameterizedTypeReference<Product>() {
                }

        );

        assertThat(getEntity.getStatusCode()).isEqualTo(HttpStatus.ACCEPTED);

        Product getProduct = getEntity.getBody();

        assertThat(getProduct.getProductId()).isEqualTo(fixture.getProductId());
        assertThat(getProduct.getName()).isEqualTo("Some new product name");
        assertThat(getProduct.getCurrentPrice().getValue()).isEqualTo(BigDecimal.valueOf(12.99));

    }

}