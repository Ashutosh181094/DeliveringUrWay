package com.example.a1505197.deliveringurway;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import de.hdodenhof.circleimageview.CircleImageView;

public class VendorInfo3 extends AppCompatActivity {
    CircleImageView Arrow3;
    //comment haif

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vendorregistration3);

        Arrow3=(CircleImageView)findViewById(R.id.arrow3);
        Arrow3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(VendorInfo3.this,Vendorinfo1.class);
                startActivity(intent);
            }
        });
    }
}
