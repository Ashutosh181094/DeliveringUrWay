package com.example.a1505197.deliveringurway;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import de.hdodenhof.circleimageview.CircleImageView;

public class RentalVendorDetails extends AppCompatActivity {

    EditText perDayCost,perHourCost;
    CircleImageView Arrow;
    String sperDayCost="",sperHourCost="";
    DatabaseReference Rentalvendordata;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rental_vendor_details);
        perDayCost=findViewById(R.id.editPerDayCost);
        perHourCost=findViewById(R.id.editCostPerHour);
        sperDayCost=perDayCost.getText().toString();
        sperHourCost=perHourCost.getText().toString();
        Arrow=(CircleImageView) findViewById(R.id.arrow3);
        Arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent getintent=getIntent();
                String nameOfB=getintent.getStringExtra("nameOfB");
                String OwnerOfB=getintent.getStringExtra("OwnerOfB");
                String Address=getintent.getStringExtra("Address");
                String PhoneNumber=getintent.getStringExtra("PhoneNumber");
                String Type=getintent.getStringExtra("type");
                Rentalvendordata= FirebaseDatabase.getInstance().getReference("vendors").child("rental");
                RentalVendorObject rentalVendorObject=new RentalVendorObject(nameOfB,OwnerOfB,Address,PhoneNumber,Type,sperDayCost,sperHourCost);
                Rentalvendordata.push().setValue(rentalVendorObject);
                Toast.makeText(getApplicationContext(), "Registered", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(RentalVendorDetails.this,VendorLogin.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            }
        });

    }
}
////