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
    String Delivery;

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

    public String getDelivery() {
        return Delivery;
    }

    public void setDelivery(String delivery) {
        Delivery = delivery;
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

    @Override
    public String toString() {
        return "VendorInformation{" +
                "nightDelivery=" + nightDelivery +
                ", payTmAccepted=" + payTmAccepted +
                ", businessName='" + businessName + '\'' +
                ", ownerName='" + ownerName + '\'' +
                ", address='" + address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", Delivery='" + Delivery + '\'' +
                '}';
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


}
