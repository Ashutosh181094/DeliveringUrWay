package com.example.a1505197.deliveringurway;

/**
 * Created by 1505197 on 3/2/2018.
 */

public class DeliveryInformation
{
    String minimumAmount;
    String Distance;
    String DeliveryChargesOtherwise;

    public DeliveryInformation(String minimumAmount, String distance, String deliveryChargesOtherwise) {
        this.minimumAmount = minimumAmount;
        Distance = distance;
        DeliveryChargesOtherwise = deliveryChargesOtherwise;
    }

    public String getMinimumAmount() {
        return minimumAmount;
    }

    public void setMinimumAmount(String minimumAmount) {
        this.minimumAmount = minimumAmount;
    }

    public String getDistance() {
        return Distance;
    }

    public void setDistance(String distance) {
        Distance = distance;
    }

    public String getDeliveryChargesOtherwise() {
        return DeliveryChargesOtherwise;
    }

    public void setDeliveryChargesOtherwise(String deliveryChargesOtherwise) {
        DeliveryChargesOtherwise = deliveryChargesOtherwise;
    }
}
