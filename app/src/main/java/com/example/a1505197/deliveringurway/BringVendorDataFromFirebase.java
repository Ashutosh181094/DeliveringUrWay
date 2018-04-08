package com.example.a1505197.deliveringurway;

import android.content.Context;
import android.widget.Toast;

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
    Context contex1t;
    private static final String TAG = "BringVendorDataFromFire";
public ArrayList<ProductDescription> getdata(Context context)
{
    contex1t=context;

    data=new ArrayList<>();
    user= FirebaseAuth.getInstance().getCurrentUser();

    Vendortotaldata= FirebaseDatabase.getInstance().getReference("productinfo").child(user.getPhoneNumber());
    Vendortotaldata.addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot)
        {



            for (DataSnapshot dataSnapshot1:dataSnapshot.getChildren())
            {

                Toast.makeText(contex1t,"This is on datachange method"+i,Toast.LENGTH_LONG).show();

          ProductDescription productDescription=dataSnapshot1.child(user.getPhoneNumber()).getValue(ProductDescription.class);
i++;
           data.add(productDescription);
                Toast.makeText(contex1t,"data added to list "+data.size(),Toast.LENGTH_LONG).show();


            }
        }


        @Override
        public void onCancelled(DatabaseError databaseError)
        {

        }
    });

    Toast.makeText(contex1t,"This is on  "+data.size(),Toast.LENGTH_LONG).show();

    return data;

}
}
