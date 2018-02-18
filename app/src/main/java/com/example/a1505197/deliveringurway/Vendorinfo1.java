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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vendorregistration1);
        Arrow=(CircleImageView) findViewById(R.id.arrow1);
        aSwitch=(Switch) findViewById(R.id.switchPaytm);
        bSwitch=findViewById(R.id.switchNightDelivery);
        Arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Vendorinfo1.this,VendorInfo2.class);
                startActivity(intent);
            }
        });
        bSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (compoundButton.isChecked())
                {
                    bSwitch.setThumbResource(R.drawable.thumbsmiley);
                }
                else
                {
                    bSwitch.setThumbResource(R.drawable.thumbsad);
                }
            }
        });
       aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
           @Override
           public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
               if(compoundButton.isChecked())
               {
                   aSwitch.setThumbResource(R.drawable.thumbsmiley);
               }
               else
               {
                   aSwitch.setThumbResource(R.drawable.thumbsad);
               }
           }
       });
    }
}
