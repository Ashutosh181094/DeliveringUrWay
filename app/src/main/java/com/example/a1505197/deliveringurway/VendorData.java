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
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class VendorData extends AppCompatActivity {

    RecyclerView VendorRecyclerView;
    FirebaseAuth mAuth;
    FloatingActionButton addVendordata;
    ArrayList<ProductDescription> data;

    private static final int REQUEST_CODE=1;
    VendorDataAdapter adapter;
    BringVendorDataFromFirebase bringVendorDataFromFirebase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendordata);

      VendorRecyclerView=findViewById(R.id.vendorrecycler);
      bringVendorDataFromFirebase=new BringVendorDataFromFirebase();
      data=bringVendorDataFromFirebase.getdata(VendorData.this);
       adapter=new VendorDataAdapter(VendorData.this,data);
        Toast.makeText(getApplicationContext(),"This is vendor data class "+data.size(),Toast.LENGTH_LONG).show();

        VendorRecyclerView.setAdapter(adapter);
        Toast.makeText(getApplicationContext(),"This is vendordatacass  "+data.size(),Toast.LENGTH_LONG).show();

        VendorRecyclerView.setLayoutManager(new LinearLayoutManager(this));
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
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
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


}
//
//