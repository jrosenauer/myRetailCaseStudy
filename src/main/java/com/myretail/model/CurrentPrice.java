package com.myretail.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.math.BigDecimal;

@Document(collection = "current_price")
public class CurrentPrice implements Serializable {

    @Id
    private int productId;
    private BigDecimal value;
    private String currencyCode;

    public CurrentPrice() {

    }

    @JsonIgnore
    @JsonProperty(value = "id")
    public int getProductId() {

        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    @Override
    public String toString() {
        return "CurrentPrice {"
                + "value = " + value + ","
                + "currencyCode = " + currencyCode + "}";
    }

}
