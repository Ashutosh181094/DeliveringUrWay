package com.example.a1505197.deliveringurway;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;

import de.hdodenhof.circleimageview.CircleImageView;

public class Vendorinfo1 extends AppCompatActivity {

    CircleImageView Arrow;
    Switch aSwitch,bSwitch;
    int nightDelivery=0;
    int payTmAccepted;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vendorregistration1);
        Arrow=(CircleImageView) findViewById(R.id.arrow1);
        aSwitch=(Switch) findViewById(R.id.switchPaytm);
        bSwitch=(Switch) findViewById(R.id.switchNightDelivery);

        Arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent getintent=getIntent();
                String nameOfB=getintent.getStringExtra("nameOfB");
                String OwnerOfB=getintent.getStringExtra("OwnerOfB");
                String Address=getintent.getStringExtra("Address");
                String PhoneNumber=getintent.getStringExtra("PhoneNumber");
                String Type=getintent.getStringExtra("type");

                Intent intent=new Intent(Vendorinfo1.this,VendorInfo4.class);
                intent.putExtra("nightDelivery",nightDelivery);
                intent.putExtra("paytmAccepted",payTmAccepted);
                intent.putExtra("Address",Address);
                intent.putExtra("PhoneNumber",PhoneNumber);
                intent.putExtra("nameOfB",nameOfB);
                intent.putExtra("OwnerOfB",OwnerOfB);
                intent.putExtra("type",Type);
                startActivity(intent);
            }
        });
        bSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (compoundButton.isChecked())
                {
                    bSwitch.setThumbResource(R.drawable.thumbsmiley);
                    nightDelivery=1;
                }
                //vv
                else
                {
                    bSwitch.setThumbResource(R.drawable.thumbsad);
                    nightDelivery=0;
                }
            }
        });
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
    }
}
////