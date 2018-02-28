package com.example.a1505197.deliveringurway;

/**
 * Created by 1505197 on 2/20/2018.
 */

public class VendorInformation {
    int nightDelivery;
    int payTmAccepted;
    String businessName;
    String ownerName;
    String address;
    String phoneNumber;
    int freeDelivery;
    int minAmount,dist,chargesOtherwise;


    public int getNightDelivery() {
        return nightDelivery;
    }

    public void setNightDelivery(int nightDelivery) {
        this.nightDelivery = nightDelivery;
    }

    public int getPayTmAccepted() {
        return payTmAccepted;
    }

    public void setPayTmAccepted(int payTmAccepted) {
        this.payTmAccepted = payTmAccepted;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getFreeDelivery() {
        return freeDelivery;
    }

    public void setFreeDelivery(int freeDelivery) {
        this.freeDelivery = freeDelivery;
    }

    public int getMinAmount() {
        return minAmount;
    }

    public void setMinAmount(int minAmount) {
        this.minAmount = minAmount;
    }

    public int getDist() {
        return dist;
    }

    public void setDist(int dist) {
        this.dist = dist;
    }

    public int getChargesOtherwise() {
        return chargesOtherwise;
    }

    public void setChargesOtherwise(int chargesOtherwise) {
        this.chargesOtherwise = chargesOtherwise;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


}
