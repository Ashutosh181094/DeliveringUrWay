package com.example.a1505197.deliveringurway;

/**
 * Created by 1505197 on 6/12/2018.
 */

public class RentalProductDescription
{
    String product_name;
    String cost_per_hour;
    String cost_per_day;
    String image_url;

    public RentalProductDescription() {
    }

    public RentalProductDescription(String product_name, String cost_per_hour, String cost_per_day, String image_url) {
        this.product_name = product_name;
        this.cost_per_hour = cost_per_hour;
        this.cost_per_day = cost_per_day;
        this.image_url = image_url;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getCost_per_hour() {
        return cost_per_hour;
    }

    public void setCost_per_hour(String cost_per_hour) {
        this.cost_per_hour = cost_per_hour;
    }

    public String getCost_per_day() {
        return cost_per_day;
    }

    public void setCost_per_day(String cost_per_day) {
        this.cost_per_day = cost_per_day;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }
}
