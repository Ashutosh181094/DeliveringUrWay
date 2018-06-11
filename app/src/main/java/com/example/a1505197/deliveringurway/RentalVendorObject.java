package com.example.a1505197.deliveringurway;

/**
 * Created by 1505197 on 6/4/2018.
 */

public class RentalVendorObject
{

    String business_name;
    String owner_name;
    String address;
    String phone_number;
    String typeofproduct;
     int pay_tm_accepted;

    public RentalVendorObject() {
    }

    public RentalVendorObject(String business_name, String owner_name, String address, String phone_number, String typeofproduct, int pay_tm_accepted) {
        this.business_name = business_name;
        this.owner_name = owner_name;
        this.address = address;
        this.phone_number = phone_number;
        this.typeofproduct = typeofproduct;
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

    public String getTypeofproduct() {
        return typeofproduct;
    }

    public void setTypeofproduct(String typeofproduct) {
        this.typeofproduct = typeofproduct;
    }

    public int getPay_tm_accepted() {
        return pay_tm_accepted;
    }

    public void setPay_tm_accepted(int pay_tm_accepted) {
        this.pay_tm_accepted = pay_tm_accepted;
    }
}
////