package com.example.a1505197.deliveringurway;

/**
 * Created by 1505197 on 2/20/2018.
 */

public class VendorInformation {
    int night_delivery;
    int pay_tm_accepted;
    String business_name;
    String owner_name;
    String address;
    String phone_number;
    String delivery_info;
    String type;
    public VendorInformation()
    {

    }

    public VendorInformation(int night_delivery, int pay_tm_accepted, String business_name, String owner_name, String address, String phone_number, String delivery_info, String type) {
        this.night_delivery = night_delivery;
        this.pay_tm_accepted = pay_tm_accepted;
        this.business_name = business_name;
        this.owner_name = owner_name;
        this.address = address;
        this.phone_number = phone_number;
        this.delivery_info = delivery_info;
        this.type = type;
    }

    public int getNight_delivery() {
        return night_delivery;
    }

    public void setNight_delivery(int night_delivery) {
        this.night_delivery = night_delivery;
    }

    public int getPay_tm_accepted() {
        return pay_tm_accepted;
    }

    public void setPay_tm_accepted(int pay_tm_accepted) {
        this.pay_tm_accepted = pay_tm_accepted;
    }

    public String getBusiness_name() {
        return business_name;
    }

    public void setBusiness_name(String business_name) {
        this.business_name = business_name;
    }

    public String getOwner_name() {
        return owner_name;
    }

    public void setOwner_name(String owner_name) {
        this.owner_name = owner_name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getDelivery_info() {
        return delivery_info;
    }

    public void setDelivery_info(String delivery_info) {
        this.delivery_info = delivery_info;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
//////////////