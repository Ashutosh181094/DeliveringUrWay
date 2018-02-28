package com.example.a1505197.deliveringurway;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
               Intent intentsend1=new Intent(Vendorinfo1.this,VendorInfo4.class);
               intentsend1.putExtra("nightDelivery",nightDelivery);
               intentsend1.putExtra("paytmAccepted",payTmAccepted);
                Intent intent=new Intent(Vendorinfo1.this,VendorInfo4.class);
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
