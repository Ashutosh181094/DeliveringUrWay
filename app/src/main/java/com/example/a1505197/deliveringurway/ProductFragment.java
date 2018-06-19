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
    ArrayList<ClothProductDescription> userSideClothProductDescription;
    ArrayList<RentalProductDescription> userSideRentalProductDescription;

    DatabaseReference Vendortotaldata;
    UserSideVendorDataFoodAdapter dataAdapter;
    UserSideVendorDataClothAdapter dataClothAdapter;
    UserSideVendorDataRentalAdapter dataRentalAdapter;
    int i=0;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_product,container,false);
        userSideVendorDataRecyclerView=view.findViewById(R.id.userSideVendorDataRecyclerView);
        userSideFoodProductDescription =new ArrayList<>();
        userSideClothProductDescription=new ArrayList<>();
        userSideRentalProductDescription=new ArrayList<>();
        Intent intent=getActivity().getIntent();
        final String phno=intent.getStringExtra("VendorPhoneNumber");
        VendorType vendorType=new VendorType();
        final String Type=vendorType.getType();
        userSideFoodProductDescription =new ArrayList<>();
        Vendortotaldata= FirebaseDatabase.getInstance().getReference("productinfo").child(""+Type).child("+91"+phno);
        Vendortotaldata.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    if (Type.equals("Food"))
                    {
                        userSideFoodProductDescription.clear();
                        for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                            FoodProductDescription foodProductDescription = dataSnapshot1.getValue(FoodProductDescription.class);
                            userSideFoodProductDescription.add(foodProductDescription);
                        }

                        dataAdapter = new UserSideVendorDataFoodAdapter(getActivity(), userSideFoodProductDescription, phno);
                        userSideVendorDataRecyclerView.setAdapter(dataAdapter);
                        userSideVendorDataRecyclerView.setHasFixedSize(true);
                        GridLayoutManager mgridlayoutmanager = new GridLayoutManager(getActivity(), 2);
                        userSideVendorDataRecyclerView.setLayoutManager(mgridlayoutmanager);
                        dataAdapter.notifyDataSetChanged();

                    }
                    else
                    if(Type.equals("Clothing"))
                    {
                        userSideClothProductDescription.clear();
                        for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                            ClothProductDescription clothProductDescription = dataSnapshot1.getValue(ClothProductDescription.class);
                            userSideClothProductDescription.add(clothProductDescription);
                        }

                        dataClothAdapter = new UserSideVendorDataClothAdapter(getActivity(), userSideClothProductDescription, phno);
                        userSideVendorDataRecyclerView.setAdapter(dataClothAdapter);
                        userSideVendorDataRecyclerView.setHasFixedSize(true);
                        GridLayoutManager mgridlayoutmanager = new GridLayoutManager(getActivity(), 2);
                        userSideVendorDataRecyclerView.setLayoutManager(mgridlayoutmanager);
                        dataClothAdapter.notifyDataSetChanged();
                    }
                    else
                    if(Type.equals("Rental"))
                    {
                        userSideRentalProductDescription.clear();
                        for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren())
                        {
                            RentalProductDescription rentalProductDescription= dataSnapshot1.getValue(RentalProductDescription.class);
                            userSideRentalProductDescription.add(rentalProductDescription);
                        }

                        dataRentalAdapter = new UserSideVendorDataRentalAdapter(getActivity(), userSideRentalProductDescription,phno);
                        userSideVendorDataRecyclerView.setAdapter(dataRentalAdapter);
                        userSideVendorDataRecyclerView.setHasFixedSize(true);
                        GridLayoutManager mgridlayoutmanager = new GridLayoutManager(getActivity(), 2);
                        userSideVendorDataRecyclerView.setLayoutManager(mgridlayoutmanager);
                        dataRentalAdapter.notifyDataSetChanged();
                    }
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