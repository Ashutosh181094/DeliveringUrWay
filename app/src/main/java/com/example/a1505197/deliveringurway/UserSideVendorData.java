package com.example.a1505197.deliveringurway;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class UserSideVendorData extends AppCompatActivity {
    RecyclerView userSideVendorDataRecyclerView;
    ArrayList<ProductDescription> userSideProductDescription;
    DatabaseReference Vendortotaldata;
    UserSideVendorDataAdapter dataAdapter;
    int i=0;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_side_vendor_data);
        userSideVendorDataRecyclerView=findViewById(R.id.userSideVendorDataRecyclerView);
        userSideProductDescription=new ArrayList<>();
        Intent intent=getIntent();
        String phno=intent.getStringExtra("VendorPhoneNumber");
        userSideProductDescription=new ArrayList<>();
        Vendortotaldata= FirebaseDatabase.getInstance().getReference("productinfo").child("+91"+phno);
        Vendortotaldata.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists())
                {

                    userSideProductDescription.clear();
                    for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren())
                    {
                        ProductDescription productDescription = dataSnapshot1.getValue(ProductDescription.class);
                        i++;
                        userSideProductDescription.add(productDescription);
                    }

                    dataAdapter = new UserSideVendorDataAdapter(UserSideVendorData.this, userSideProductDescription);
                    userSideVendorDataRecyclerView.setAdapter(dataAdapter);
                    userSideVendorDataRecyclerView.setHasFixedSize(true);
                    GridLayoutManager mgridlayoutmanager = new GridLayoutManager(getApplicationContext(), 2);
                    userSideVendorDataRecyclerView.setLayoutManager(mgridlayoutmanager);
                    dataAdapter.notifyDataSetChanged();

                }
            }


            @Override
            public void onCancelled(DatabaseError databaseError)
            {
                Toast.makeText(UserSideVendorData.this,"Database error",Toast.LENGTH_LONG).show();

            }
        });




    }
}
//