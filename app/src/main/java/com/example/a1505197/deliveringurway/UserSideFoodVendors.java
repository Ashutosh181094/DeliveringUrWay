package com.example.a1505197.deliveringurway;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class UserSideFoodVendors extends AppCompatActivity {
    RecyclerView VendorRecyclerView;
    ArrayList<VendorInformation> vendorInformations;
    DatabaseReference Vendorcontactinfo;
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
                        if(vendorInformation.type.compareTo("Food")==0)
                        {
                            vendorInformations.add(vendorInformation);
                            Toast.makeText(getApplicationContext(),""+vendorInformation.business_name,Toast.LENGTH_SHORT).show();

                        }



                    }
                    Totaldata totaldata=new Totaldata();
                    totaldata.setVendorsdata(vendorInformations);
                    VendorsAdapter vendorsAdapter=new VendorsAdapter(UserSideFoodVendors.this,vendorInformations);
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
//