package com.example.a1505197.deliveringurway;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class VendorData extends AppCompatActivity {

    RecyclerView VendorRecyclerView;
    FirebaseAuth mAuth;
    FloatingActionButton addVendordata;
    DatabaseReference Vendortotaldata,VendorTypeInfo;
    ArrayList<FoodProductDescription> data;
    ArrayList<RentalProductDescription> data2;
    ArrayList<RetrieveVendorType> vendortype;
    FirebaseUser user;
    ImageView logOut;



    private static final int REQUEST_CODE=1;
    VendorDataAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendordata);
        logOut=findViewById(R.id.logOut);
        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(VendorData.this);
                builder.setCancelable(false);
                builder.setMessage("Do you want to Logout?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Logout();
                        //if user pressed "yes", then he is allowed to exit from application
                        //finish();
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //if user select "No", just cancel this dialog and continue with app
                        dialog.cancel();
                    }
                });
                AlertDialog alert = builder.create();
                alert.show();
                //Logout();
            }
        });

        VendorRecyclerView=findViewById(R.id.vendorrecycler);





        user = FirebaseAuth.getInstance().getCurrentUser();

        data=new ArrayList<>();
        data2=new ArrayList<>();
        vendortype=new ArrayList<>();
        VendorTypeInfo=FirebaseDatabase.getInstance().getReference("VendorsType").child(""+user.getPhoneNumber());
        VendorTypeInfo.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists())
                {
                    vendortype.clear();
                    for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren())
                    {
                        RetrieveVendorType retrieveVendorType= dataSnapshot1.getValue(RetrieveVendorType.class);
                        vendortype.add(retrieveVendorType);

                    }
                }
                Vendortotaldata= FirebaseDatabase.getInstance().getReference("productinfo").child(""+vendortype.get(0).type).child(user.getPhoneNumber());
                Vendortotaldata.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists())
                        {
                            if (vendortype.get(0).type.equals("Food")||vendortype.get(0).type.equals("Clothing"))
                            {
                                data.clear();
                                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren())
                                {
                                    FoodProductDescription foodProductDescription = dataSnapshot1.getValue(FoodProductDescription.class);
                                    data.add(foodProductDescription);
                                }
                                adapter = new VendorDataAdapter(VendorData.this, data);
                                VendorRecyclerView.setAdapter(adapter);
                                VendorRecyclerView.setHasFixedSize(true);
                                GridLayoutManager mgridlayoutmanager = new GridLayoutManager(getApplicationContext(), 2);
                                VendorRecyclerView.setLayoutManager(mgridlayoutmanager);
                                adapter.notifyDataSetChanged();

                            }
                            else
                            {
                                data2.clear();
                                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren())
                                {
                                    RentalProductDescription rentalProductDescription= dataSnapshot1.getValue(RentalProductDescription.class);
                                    data2.add(rentalProductDescription);
                                }
                               VendorDataRentalAdapter adapter2 = new VendorDataRentalAdapter(VendorData.this, data2);
                                VendorRecyclerView.setAdapter(adapter2);
                                VendorRecyclerView.setHasFixedSize(true);
                                GridLayoutManager mgridlayoutmanager = new GridLayoutManager(getApplicationContext(), 2);
                                VendorRecyclerView.setLayoutManager(mgridlayoutmanager);
                                adapter2.notifyDataSetChanged();
                            }



                        }
                    }


                    @Override
                    public void onCancelled(DatabaseError databaseError)
                    {

                    }
                });



            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });






        mAuth=FirebaseAuth.getInstance();
        addVendordata=findViewById(R.id.addVendorData);
        initimageLoader();
    addVendordata.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v)
        {
            VendorTypeInfo=FirebaseDatabase.getInstance().getReference("VendorsType").child(""+user.getPhoneNumber());
            VendorTypeInfo.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists())
                    {
                        vendortype.clear();
                        for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren())
                        {
                            RetrieveVendorType retrieveVendorType= dataSnapshot1.getValue(RetrieveVendorType.class);
                            vendortype.add(retrieveVendorType);

                        }
                        Toast.makeText(getApplicationContext(),""+vendortype.get(0).type,Toast.LENGTH_LONG).show();
                    }
                    if (vendortype.get(0).type.equals("Food"))
                    {
                        AddFoodProductFragement fragment=new AddFoodProductFragement();
                        FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
                        transaction.replace(android.R.id.content,fragment);
                        transaction.addToBackStack(null);
                        transaction.commit();


                    }
                    else
                    if(vendortype.get(0).type.equals("Clothing"))
                    {
                        AddClothProductFragment fragment=new AddClothProductFragment();
                        FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
                        transaction.replace(android.R.id.content,fragment);
                        transaction.addToBackStack(null);
                        transaction.commit();

                    }
                    else
                    {
                        AddRentalProductFragment fragment=new AddRentalProductFragment();
                        FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
                        transaction.replace(android.R.id.content,fragment);
                        transaction.addToBackStack(null);
                        transaction.commit();
                    }

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });



        }
    });
    }


    @Override
    protected void onStart() {
        super.onStart();
         user = FirebaseAuth.getInstance().getCurrentUser();

        if (user == null)
        {
            Intent intent = new Intent(VendorData.this, Login.class);
            startActivity(intent);
            finish();
        }



    }
    public void verifyPermissions(String[] permissions)
    {
        ActivityCompat.requestPermissions(VendorData.this,permissions,REQUEST_CODE);
    }
    public boolean checkPermissions(String[] permission)
    {
        int permissionRequest=ActivityCompat.checkSelfPermission(VendorData.this,permission[0]);
        if(permissionRequest!= PackageManager.PERMISSION_GRANTED)
        {
            return false;
        }
        else
        {
            return true;
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
    public Bitmap compressBitmap(Bitmap bitmap, int quality)
    {
        ByteArrayOutputStream stream=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,quality,stream);
        return bitmap;
    }
    private  void initimageLoader()
    {
        UniversalImageLoader universalImageLoader=new UniversalImageLoader(VendorData.this);
        ImageLoader.getInstance().init(universalImageLoader.getConfig());
    }


    private void Logout()
    {
        mAuth.signOut();
        Intent intent=new Intent(VendorData.this,Login.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onStop() {
        super.onStop();


    }


}
//
/////////////