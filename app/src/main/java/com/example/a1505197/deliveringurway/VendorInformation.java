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


    public VendorInformation(int nightDelivery, int payTmAccepted, String businessName, String ownerName, String address, String phoneNumber) {
        this.nightDelivery = nightDelivery;
        this.payTmAccepted = payTmAccepted;
        this.businessName = businessName;
        this.ownerName = ownerName;
        this.address = address;
        this.phoneNumber = phoneNumber;

    }



    public int getNightDelivery() {
        return nightDelivery;
    }


    public int getPayTmAccepted() {
        return payTmAccepted;
    }


    public String getBusinessName() {
        return businessName;
    }



    public String getOwnerName() {
        return ownerName;

    }


    public String getAddress() {
        return address;
    }




    public String getPhoneNumber() {
        return phoneNumber;
    }



}
