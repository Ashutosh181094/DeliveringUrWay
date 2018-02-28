package com.example.a1505197.deliveringurway;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import de.hdodenhof.circleimageview.CircleImageView;

public class VendorInfo3 extends AppCompatActivity {
    CircleImageView Arrow3;
    TextView Address;
    TextView phoneNumber;
    String sAddress,sPhoneNumber;
    VendorInformation vendorInformation;
    //comment haif

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vendorregistration3);
        Address=findViewById(R.id.tvAddress);
        phoneNumber=findViewById(R.id.tvPhoneNumber);
        vendorInformation=new VendorInformation();

        Arrow3=(CircleImageView)findViewById(R.id.arrow3);
        Arrow3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sAddress=Address.getText().toString();
                sPhoneNumber=phoneNumber.getText().toString();
               Intent intentsend3=new Intent(VendorInfo3.this,VendorInfo4.class);
               intentsend3.putExtra("Address",sAddress);
               intentsend3.putExtra("PhoneNumber",sPhoneNumber);
                Intent intent=new Intent(VendorInfo3.this,Vendorinfo1.class);
                startActivity(intent);
            }
        });
    }
}
