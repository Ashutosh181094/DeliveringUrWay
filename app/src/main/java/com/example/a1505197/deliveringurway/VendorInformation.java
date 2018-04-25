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
    String deliveryInfo;
    String type;
    public VendorInformation()
    {

    }

    public VendorInformation(int nightDelivery, int payTmAccepted, String businessName, String ownerName, String address, String phoneNumber, String deliveryInfo, String type) {
        this.nightDelivery = nightDelivery;
        this.payTmAccepted = payTmAccepted;
        this.businessName = businessName;
        this.ownerName = ownerName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        deliveryInfo = deliveryInfo;
        this.type = type;
    }

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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getDeliveryInfo() {
        return deliveryInfo;
    }

    public void setDeliveryInfo(String deliveryInfo) {
        deliveryInfo = deliveryInfo;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
//