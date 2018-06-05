package com.example.a1505197.deliveringurway;

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
    DatabaseReference Vendortotaldata;
    ArrayList<ProductDescription> data;
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
                Logout();
            }
        });

        VendorRecyclerView=findViewById(R.id.vendorrecycler);





        user = FirebaseAuth.getInstance().getCurrentUser();

        data=new ArrayList<>();
        Vendortotaldata= FirebaseDatabase.getInstance().getReference("productinfo").child(user.getPhoneNumber());
        Vendortotaldata.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists())
                {

                    data.clear();
                    for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren())
                    {
                        ProductDescription productDescription = dataSnapshot1.getValue(ProductDescription.class);
                        data.add(productDescription);
                    }
                    adapter = new VendorDataAdapter(VendorData.this, data);
                    VendorRecyclerView.setAdapter(adapter);
                    VendorRecyclerView.setHasFixedSize(true);
                    GridLayoutManager mgridlayoutmanager = new GridLayoutManager(getApplicationContext(), 2);
                    VendorRecyclerView.setLayoutManager(mgridlayoutmanager);
                    adapter.notifyDataSetChanged();

                }
            }


            @Override
            public void onCancelled(DatabaseError databaseError)
            {

            }
        });




        mAuth=FirebaseAuth.getInstance();
        addVendordata=findViewById(R.id.addVendorData);
        initimageLoader();
    addVendordata.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v)
        {
            AddProductFragement fragment=new AddProductFragement();
            FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
            transaction.replace(android.R.id.content,fragment);
            transaction.addToBackStack(null);
            transaction.commit();


        }
    });
    }


    @Override
    protected void onStart() {
        super.onStart();
         user = FirebaseAuth.getInstance().getCurrentUser();
        if (user == null) {
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
//////////