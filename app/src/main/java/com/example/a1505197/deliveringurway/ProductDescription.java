package com.example.a1505197.deliveringurway;

/**
 * Created by 1505197 on 3/20/2018.
 */

public class ProductDescription
{
    String product_name;
    String cost;
    String description;
    String image_url;
    public ProductDescription()
    {

    }

    public ProductDescription(String product_name, String cost, String description, String image_url) {
        this.product_name = product_name;
        this.cost = cost;
        this.description = description;
        this.image_url = image_url;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
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

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }
}
////////////