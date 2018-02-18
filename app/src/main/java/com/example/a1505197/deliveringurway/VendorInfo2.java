package com.example.a1505197.deliveringurway;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import de.hdodenhof.circleimageview.CircleImageView;

public class VendorInfo2 extends AppCompatActivity {

    CircleImageView Arrow2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vendorregistration2);
        Arrow2=(CircleImageView) findViewById(R.id.arrow2);
        Arrow2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(VendorInfo2.this,VendorInfo3.class);
                startActivity(intent);

            }
        });
    }
}
