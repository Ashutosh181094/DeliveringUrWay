package com.example.a1505197.deliveringurway;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import de.hdodenhof.circleimageview.CircleImageView;

public class RentalVendorDetails extends AppCompatActivity {

    EditText perDayCost,perHourCost;
    CircleImageView Arrow;
    int payTmAccepted;
    Switch aSwitch;
    DatabaseReference Rentalvendordata;
    DatabaseReference VendorTypeInfo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rental_vendor_details);

        aSwitch=(Switch) findViewById(R.id.switchPaytm);
        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(compoundButton.isChecked())
                {
                    aSwitch.setThumbResource(R.drawable.thumbsmiley);
                    payTmAccepted=1;
                }
                else
                {
                    aSwitch.setThumbResource(R.drawable.thumbsad);
                    payTmAccepted=0;
                }
            }
        });

        Arrow=(CircleImageView) findViewById(R.id.arrow3);
        Arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {

                Intent getintent=getIntent();
                String nameOfB=getintent.getStringExtra("nameOfB");
                String OwnerOfB=getintent.getStringExtra("OwnerOfB");
                String Address=getintent.getStringExtra("Address");
                String PhoneNumber=getintent.getStringExtra("PhoneNumber");
                String Type=getintent.getStringExtra("type");

                Rentalvendordata= FirebaseDatabase.getInstance().getReference("vendors").child("rental");
                VendorTypeInfo=FirebaseDatabase.getInstance().getReference("VendorsType").child("+91"+PhoneNumber);

                RentalVendorObject rentalVendorObject=new RentalVendorObject(nameOfB,OwnerOfB,Address,PhoneNumber,Type,payTmAccepted);
                Rentalvendordata.push().setValue(rentalVendorObject);

                RetrieveVendorType retrieveVendorType=new RetrieveVendorType(Type);
                VendorTypeInfo.push().setValue(retrieveVendorType);

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