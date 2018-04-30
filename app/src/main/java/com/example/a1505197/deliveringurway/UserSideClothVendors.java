package com.example.a1505197.deliveringurway;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class UserSideClothVendors extends AppCompatActivity {
    RecyclerView VendorRecyclerView;
    ArrayList<VendorInformation> vendorInformations;
    DatabaseReference Vendorcontactinfo;
    public static int i=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_side_vendors);
        VendorRecyclerView=findViewById(R.id.userSideRecyclerView);
        vendorInformations=new ArrayList<>();
        Vendorcontactinfo = FirebaseDatabase.getInstance().getReference("vendors");
        Vendorcontactinfo.addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot)
            {
                if (dataSnapshot.exists())
                {

                    vendorInformations.clear();

                    for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren())
                    {
                        VendorInformation vendorInformation = dataSnapshot1.getValue(VendorInformation.class);
                        i++;
                        if(vendorInformation.type.compareTo("Clothing")==0)
                        {
                            vendorInformations.add(vendorInformation);
                        }


                    }
                    VendorsAdapter vendorsAdapter=new VendorsAdapter(UserSideClothVendors.this,vendorInformations);
                    VendorRecyclerView.setAdapter(vendorsAdapter);
                    VendorRecyclerView.setHasFixedSize(true);
                    VendorRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

                    vendorsAdapter.notifyDataSetChanged();

                }

            }


            @Override
            public void onCancelled(DatabaseError databaseError)
            {

            }
        });
    }
}
