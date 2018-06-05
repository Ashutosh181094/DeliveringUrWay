package com.example.a1505197.deliveringurway;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class VendorProductDescription extends AppCompatActivity {
    String name,cost,description,imageurl;
    ImageView imageView;
    TextView tvCost,tvName,tvDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendor_product_description);
        Intent intent=getIntent();
        name=intent.getStringExtra("name");
        cost= intent.getStringExtra("cost");
        description= intent.getStringExtra("description");
        imageurl=intent.getStringExtra("imageurl");

        imageView=findViewById(R.id.itemImage);
        tvCost=findViewById(R.id.tvCostCardView);
        tvName=findViewById(R.id.tvNameCardView);
        tvDescription=findViewById(R.id.tvdescriptionCardView);
        Picasso.with(this)
                .load(imageurl)
                .fit()
                .centerCrop()
                .into(imageView);
        tvCost.setText(cost);
        tvName.setText(name);
        tvDescription.setText(description);

    }
}
////////