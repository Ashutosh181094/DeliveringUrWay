package com.example.a1505197.deliveringurway;

/**
 * Created by 1505197 on 6/12/2018.
 */

public class ClothProductDescription
{
    String product_name;
    String cost;
    String size_available;
    String description;
    String image_url;

    public ClothProductDescription() {
    }

    public ClothProductDescription(String product_name, String cost, String size_available, String description, String image_url) {
        this.product_name = product_name;
        this.cost = cost;
        this.size_available = size_available;
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

    public String getSize_available() {
        return size_available;
    }

    public void setSize_available(String size_available) {
        this.size_available = size_available;
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
//