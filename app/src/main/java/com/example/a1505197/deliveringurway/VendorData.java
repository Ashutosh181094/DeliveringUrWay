package com.example.a1505197.deliveringurway;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
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
    DatabaseReference Vendortotaldata;
    ArrayList<ProductDescription> data;
    FirebaseUser user;
    Toolbar toolbar;
    static int i=0;


    private static final int REQUEST_CODE=1;
    VendorDataAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendordata);
        SharedPreferences sp = getPreferences(MODE_PRIVATE);
        i = sp.getInt("iValue", 0);
        Toast.makeText(VendorData.this,""+i,Toast.LENGTH_LONG).show();
        toolbar = (Toolbar) findViewById(R.id.customToolbarLayout);
        VendorRecyclerView=findViewById(R.id.vendorrecycler);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("DUR WAY");


        getSupportActionBar().setElevation(10f);

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
                        i++;
                        data.add(productDescription);
                    }
                    Totaldata totaldata=new Totaldata();
                    totaldata.setProductsdata(data);
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
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        if(item.getItemId()==R.id.aboutus)
        {
            Logout();
        }


        else if(item.getItemId()==R.id.share)
        {
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            startActivity(shareIntent);
        }
        else if(item.getItemId()==R.id.email)
        {
            Intent intent=new Intent(Intent.ACTION_SEND);
            intent.setData(Uri.parse("Mailto:"));
            String [] to={"ashutoshrai181094@gmail.com"};
            intent.putExtra(Intent.EXTRA_EMAIL,to);
            intent.putExtra(Intent.EXTRA_SUBJECT,"Hi this was sent from my app");
            intent.putExtra(Intent.EXTRA_TEXT,"Hey whats up what are u doing");
            intent.setType("text/html");
            startActivity(Intent.createChooser(intent, "Send Email"));
        }

        else
        {

        }

        return super.onOptionsItemSelected(item);
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

        SharedPreferences.Editor spe = getPreferences(MODE_PRIVATE).edit();
        spe.putInt("iValue", i);
        spe.commit();
    }
}
//
//////