package com.example.a1505197.deliveringurway;

/**
 * Created by 1505197 on 3/2/2018.
 */

public class DeliveryInformation {
    String minimum_amount;
    String distance;
    String delivery_charges_otherwise;

    public DeliveryInformation() {

    }

    public DeliveryInformation(String minimum_amount, String distance, String delivery_charges_otherwise) {
        this.minimum_amount = minimum_amount;
        this.distance = distance;
        this.delivery_charges_otherwise = delivery_charges_otherwise;
    }

    public String getMinimum_amount() {
        return minimum_amount;
    }

    public void setMinimum_amount(String minimum_amount) {
        this.minimum_amount = minimum_amount;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getDelivery_charges_otherwise() {
        return delivery_charges_otherwise;
    }

    public void setDelivery_charges_otherwise(String delivery_charges_otherwise) {
        this.delivery_charges_otherwise = delivery_charges_otherwise;
    }
}
/////////