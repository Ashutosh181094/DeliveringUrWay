package com.example.a1505197.deliveringurway;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by 1505197 on 3/26/2018.
 */

public class BringVendorDataFromFirebase
{
DatabaseReference Vendortotaldata;
ArrayList<ProductDescription> data;
FirebaseUser user;
static int i=0;
    private static final String TAG = "BringVendorDataFromFire";
public ArrayList<ProductDescription> getdata()
{
    data=new ArrayList<>();
    Vendortotaldata= FirebaseDatabase.getInstance().getReference("productinfo");
    Vendortotaldata.addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot)
        {

            if(dataSnapshot.exists())
            user= FirebaseAuth.getInstance().getCurrentUser();

            for (DataSnapshot dataSnapshot1:dataSnapshot.getChildren())
            {


          ProductDescription productDescription=dataSnapshot1.child(user.getPhoneNumber()).child(""+i++).getValue(ProductDescription.class);

           data.add(productDescription);

            }
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    });
    return data;

}
}
