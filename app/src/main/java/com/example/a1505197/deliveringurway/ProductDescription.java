package com.example.a1505197.deliveringurway;

/**
 * Created by 1505197 on 3/20/2018.
 */

public class ProductDescription
{
    String productName;
    String cost;
    String description;
    String imageUrl;
    public ProductDescription()
    {

    }

    public ProductDescription(String productName, String cost, String description, String imageUrl) {
        this.productName = productName;
        this.cost = cost;
        this.description = description;
        this.imageUrl = imageUrl;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getProductName() {
        return productName;
    }

    public String getCost() {
        return cost;
    }

    public String getDescription() {
        return description;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
