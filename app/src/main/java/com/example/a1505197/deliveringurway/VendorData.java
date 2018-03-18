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
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.io.ByteArrayOutputStream;

public class VendorData extends AppCompatActivity {

    FirebaseAuth mAuth;
    FloatingActionButton addVendordata;
    private static final int REQUEST_CODE=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendordata);

        mAuth=FirebaseAuth.getInstance();
        addVendordata=findViewById(R.id.addVendorData);
        initimageLoader();
    addVendordata.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v)
        {
            AddProductFragement fragment=new AddProductFragement();
            FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container,fragment);
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