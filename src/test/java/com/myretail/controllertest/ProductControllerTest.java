package com.myretail.controllertest;

import com.myretail.controller.ProductController;
import com.myretail.model.CurrentPrice;
import com.myretail.model.Product;
import com.myretail.service.ProductSearchServiceImpl;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.omg.CORBA.Current;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.math.BigDecimal;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = ProductController.class)
@WebMvcTest(value = ProductController.class, secure = false)
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    @Qualifier(value = "productSearchService")
    private ProductSearchServiceImpl productSearchService;

    private int movie_id = 13860428;
    Product product = null;
    CurrentPrice currentPrice = new CurrentPrice();

    @Before
    public void setUp() {
        currentPrice.setProductId(movie_id);
        currentPrice.setCurrencyCode("USD");
        currentPrice.setValue(BigDecimal.valueOf(13.49));
        product = new Product(movie_id, "The Big Lebowski (Blue-ray)", currentPrice);
    }

    @Test
    public void getProductDetailsTest() throws Exception {
        Mockito.when(productSearchService.getProductSearch(movie_id)).thenReturn(product);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/product/" + movie_id).accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        String expectedResult = "{\"productId\":13860428,\"name\":\"The Big Lebowski (Blue-ray)\",\"currentPrice\":{\"value\":13.49,\"currencyCode\":\"USD\"}}";
        JSONAssert.assertEquals(expectedResult, result.getResponse().getContentAsString(), false);
    }


    @Test(expected = MethodArgumentTypeMismatchException.class)
    public void getProductDetailsBadRequestTest() throws Exception, MethodArgumentTypeMismatchException {
        String variable = "ABC";
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/product/" + variable).accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        Assert.assertEquals(HttpStatus.BAD_REQUEST.value(), result.getResponse().getStatus());
        throw result.getResolvedException();
    }
}