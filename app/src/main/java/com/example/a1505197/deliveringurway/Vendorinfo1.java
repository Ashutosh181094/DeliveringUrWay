package com.example.a1505197.deliveringurway;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import de.hdodenhof.circleimageview.CircleImageView;

public class Vendorinfo1 extends AppCompatActivity {

    CircleImageView Arrow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vendorregistration1);
        Arrow=(CircleImageView) findViewById(R.id.arrow1);
        Arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Vendorinfo1.this,VendorInfo2.class);
                startActivity(intent);
            }
        });
    }
}
