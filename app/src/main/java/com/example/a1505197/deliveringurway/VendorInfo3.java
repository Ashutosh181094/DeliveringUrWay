package com.example.a1505197.deliveringurway;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;

import de.hdodenhof.circleimageview.CircleImageView;

public class VendorInfo3 extends AppCompatActivity {
    CircleImageView Arrow3;
    String sAddress,sPhoneNumber;
    EditText vAddress,vPhoneNumber;
    TextView tvAddress;
    int PLACE_PICKER_REQUEST=1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vendorregistration3);
        vAddress=findViewById(R.id.editAddress);
        vPhoneNumber=findViewById(R.id.editPhoneNumber);
        tvAddress=findViewById(R.id.tvAddress);
        tvAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PlacePicker.IntentBuilder builder=new PlacePicker.IntentBuilder();

                try {
                    Intent intent=builder.build(VendorInfo3.this);
                    startActivityForResult(intent,PLACE_PICKER_REQUEST);
                } catch (GooglePlayServicesRepairableException e) {
                    e.printStackTrace();
                } catch (GooglePlayServicesNotAvailableException e) {
                    e.printStackTrace();
                }

            }
        });

        Arrow3=(CircleImageView)findViewById(R.id.arrow3);
        Arrow3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sAddress=vAddress.getText().toString();
                sPhoneNumber=vPhoneNumber.getText().toString();
               Intent intentsend3=getIntent();
                String nameOfB=intentsend3.getStringExtra("nameOfB");
                String OwnerOfB=intentsend3.getStringExtra("OwnerOfB");
                String Type=intentsend3.getStringExtra("type");

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

                        if(Type.equals("Rental"))
                        {
                            Intent intent = new Intent(VendorInfo3.this, RentalVendorDetails.class);
                            intent.putExtra("Address", sAddress);
                            intent.putExtra("PhoneNumber", sPhoneNumber);
                            intent.putExtra("nameOfB", nameOfB);
                            intent.putExtra("OwnerOfB", OwnerOfB);
                            intent.putExtra("type",Type);
                            startActivity(intent);


                        }
                        else
                        {
                            Intent intent = new Intent(VendorInfo3.this, Vendorinfo1.class);
                            intent.putExtra("Address", sAddress);
                            intent.putExtra("PhoneNumber", sPhoneNumber);
                            intent.putExtra("nameOfB", nameOfB);
                            intent.putExtra("OwnerOfB", OwnerOfB);
                            intent.putExtra("type",Type);
                            startActivity(intent);
                        }
                    }
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PLACE_PICKER_REQUEST) {

            if (resultCode== RESULT_OK) {
                Place place=PlacePicker.getPlace(data,this);
                String address=String.format("%s", place.getAddress());
                vAddress.setText(address);
            }
        }
    }
}
////
//