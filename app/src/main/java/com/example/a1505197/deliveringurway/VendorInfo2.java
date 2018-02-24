package com.example.a1505197.deliveringurway;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import de.hdodenhof.circleimageview.CircleImageView;

public class VendorInfo2 extends AppCompatActivity {
   EditText nameOfBusiness;
   EditText ownerOfBusiness;
   String nameOfB="";
   String OwnerOfB="";
    CircleImageView Arrow2;
    VendorInformation vendorInformation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vendorregistration2);
        Arrow2=(CircleImageView) findViewById(R.id.arrow2);
        nameOfBusiness=(EditText) findViewById(R.id.nameOfBusiness);
        ownerOfBusiness=(EditText) findViewById(R.id.ownerOfBusiness);
        vendorInformation=new VendorInformation();
        Arrow2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nameOfB = nameOfBusiness.getText().toString();
                OwnerOfB = ownerOfBusiness.getText().toString();
                if (OwnerOfB.equals("") || nameOfB.equals("")) {
                    Toast.makeText(getApplicationContext(), "Please fill all fields", Toast.LENGTH_SHORT).show();
                } else
                {
                vendorInformation.setBusinessName(nameOfB);
                vendorInformation.setOwnerName(OwnerOfB);
                Intent intent = new Intent(VendorInfo2.this, VendorInfo3.class);
                startActivity(intent);
            }

            }
        });
    }
}
