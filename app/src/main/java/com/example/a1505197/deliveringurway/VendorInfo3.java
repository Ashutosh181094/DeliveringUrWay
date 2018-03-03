package com.example.a1505197.deliveringurway;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import de.hdodenhof.circleimageview.CircleImageView;

public class VendorInfo3 extends AppCompatActivity {
    CircleImageView Arrow3;
    String sAddress,sPhoneNumber;
    EditText vAddress,vPhoneNumber;

    //comment haif

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vendorregistration3);
        vAddress=findViewById(R.id.editAddress);
        vPhoneNumber=findViewById(R.id.editPhoneNumber);

        Arrow3=(CircleImageView)findViewById(R.id.arrow3);
        Arrow3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sAddress=vAddress.getText().toString();
                sPhoneNumber=vPhoneNumber.getText().toString();
               Intent intentsend3=getIntent();
                String nameOfB=intentsend3.getStringExtra("nameOfB");
                String OwnerOfB=intentsend3.getStringExtra("OwnerOfB");

                if(sAddress.equals("")){
                    vAddress.setError("Please enter address");
                }
                if(sPhoneNumber.equals("")){
                    vPhoneNumber.setError("Please enter phone number");
                }

                else {

                    if(sPhoneNumber.length()>0 && sPhoneNumber.length()!=10){
                        vPhoneNumber.setError("Invalid mobile number.10 digits allowed");
                    }
                    else {
                        Intent intent = new Intent(VendorInfo3.this, Vendorinfo1.class);
                        intent.putExtra("Address", sAddress);
                        intent.putExtra("PhoneNumber", sPhoneNumber);
                        intent.putExtra("nameOfB", nameOfB);
                        intent.putExtra("OwnerOfB", OwnerOfB);
                        startActivity(intent);
                    }
                }
            }
        });
    }
}
//