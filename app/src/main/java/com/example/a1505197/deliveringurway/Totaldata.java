package com.example.a1505197.deliveringurway;

import java.util.ArrayList;

/**
 * Created by 1505197 on 4/26/2018.
 */

public class Totaldata
{
    ArrayList<VendorInformation> Vendorsdata;
    ArrayList<ProductDescription> productsdata;
   public Totaldata()
   {

   }
    public Totaldata(ArrayList<VendorInformation> vendorsdata, ArrayList<ProductDescription> productsdata) {
        Vendorsdata = vendorsdata;
        this.productsdata = productsdata;
    }

    public ArrayList<VendorInformation> getVendorsdata() {
        return Vendorsdata;
    }

    public void setVendorsdata(ArrayList<VendorInformation> vendorsdata) {
        Vendorsdata = vendorsdata;
    }

    public ArrayList<ProductDescription> getProductsdata() {
        return productsdata;
    }

    public void setProductsdata(ArrayList<ProductDescription> productsdata) {
        this.productsdata = productsdata;
    }
}
//
//