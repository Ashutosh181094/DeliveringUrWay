package com.example.a1505197.deliveringurway;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import de.hdodenhof.circleimageview.CircleImageView;

public class VendorInfo3 extends AppCompatActivity {
    CircleImageView Arrow3;
    TextView Address;
    TextView phoneNumber;
    String sAddress,sPhoneNumber;
    EditText vAddress,vPhoneNumber;

    //comment haif

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vendorregistration3);
        vAddress=(EditText) findViewById(R.id.editAddress);
        vPhoneNumber=(EditText) findViewById(R.id.editPhoneNumber);

        Arrow3=(CircleImageView)findViewById(R.id.arrow3);
        Arrow3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sAddress=vAddress.getText().toString();
                sPhoneNumber=vPhoneNumber.getText().toString();
               Intent intentsend3=getIntent();
                String nameOfB=intentsend3.getStringExtra("nameOfB");
                String OwnerOfB=intentsend3.getStringExtra("OwnerOfB");

                Intent intent=new Intent(VendorInfo3.this,Vendorinfo1.class);
                intent.putExtra("Address",sAddress);
                intent.putExtra("PhoneNumber",sPhoneNumber);
                intent.putExtra("nameOfB",nameOfB);
                intent.putExtra("OwnerOfB",OwnerOfB);
                startActivity(intent);
            }
        });
    }
}
