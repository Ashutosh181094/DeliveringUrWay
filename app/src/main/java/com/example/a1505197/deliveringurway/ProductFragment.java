package com.example.a1505197.deliveringurway;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class ProductFragment extends Fragment
{
    RecyclerView userSideVendorDataRecyclerView;
    ArrayList<FoodProductDescription> userSideFoodProductDescription;
    DatabaseReference Vendortotaldata;
    UserSideVendorDataAdapter dataAdapter;
    int i=0;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_product,container,false);
        userSideVendorDataRecyclerView=view.findViewById(R.id.userSideVendorDataRecyclerView);
        userSideFoodProductDescription =new ArrayList<>();
        Intent intent=getActivity().getIntent();
        final String phno=intent.getStringExtra("VendorPhoneNumber");
        userSideFoodProductDescription =new ArrayList<>();
        Vendortotaldata= FirebaseDatabase.getInstance().getReference("productinfo").child("+91"+phno);
        Vendortotaldata.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists())
                {

                    userSideFoodProductDescription.clear();
                    for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren())
                    {
                        FoodProductDescription foodProductDescription = dataSnapshot1.getValue(FoodProductDescription.class);
                        userSideFoodProductDescription.add(foodProductDescription);
                    }

                    dataAdapter = new UserSideVendorDataAdapter(getActivity(), userSideFoodProductDescription,phno);
                    userSideVendorDataRecyclerView.setAdapter(dataAdapter);
                    userSideVendorDataRecyclerView.setHasFixedSize(true);
                    GridLayoutManager mgridlayoutmanager = new GridLayoutManager(getActivity(), 2);
                    userSideVendorDataRecyclerView.setLayoutManager(mgridlayoutmanager);
                    dataAdapter.notifyDataSetChanged();

                }
            }


            @Override
            public void onCancelled(DatabaseError databaseError)
            {
                Toast.makeText(getActivity(),"Database error",Toast.LENGTH_LONG).show();

            }
        });
        return view;
    }
}
//////////