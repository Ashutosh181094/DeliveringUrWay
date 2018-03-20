package com.example.a1505197.deliveringurway;

/**
 * Created by 1505197 on 3/20/2018.
 */

public class ProductDescription
{
    String productName;
    String cost;
    String description;

    public ProductDescription(String productName, String cost, String description) {
        this.productName = productName;
        this.cost = cost;
        this.description = description;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
