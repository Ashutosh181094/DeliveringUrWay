package com.example.a1505197.deliveringurway;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import java.io.ByteArrayOutputStream;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener  {
    CarouselView carouselView;
    Toolbar toolbar;
    FirebaseAuth mAuth;
    TextView tvFood,tvClothes;
    private static final int REQUEST_CODE=1;
    CircleImageView userCircleImage;
    DatabaseReference userData;
    int[] sampleImages = {R.drawable.hut, R.drawable.hut, R.drawable.hut, R.drawable.hut, R.drawable.hut};
     String userName="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth=FirebaseAuth.getInstance();
        tvFood=findViewById(R.id.tvFood);
        tvClothes=findViewById(R.id.tvClothing);
       // userName=Register.getActivityInstance().getData();
        /*
        userCircleImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(int i = 0; i< Init.PERMISSIONS.length; i++)
                {
                    String[] permission={Init.PERMISSIONS[i]};
                    if(checkPermissions(permission))
                    {
                        if(i==Init.PERMISSIONS.length-1)
                        {
                            ChangePhotoDialog dialog=new ChangePhotoDialog();

                        }
                    }
                    else
                    {
                        verifyPermissions(permission);
                    }
                }
            }
        });
        */

        carouselView = (CarouselView) findViewById(R.id.carouselView);
        toolbar = (Toolbar) findViewById(R.id.customToolbarLayout);
        carouselView.setPageCount(sampleImages.length);
        carouselView.setImageListener(imageListener);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("DUR WAY");
        getSupportActionBar().setElevation(10f);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        tvFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,UserSideFoodVendors.class);
                startActivity(intent);
            }
        });
        tvClothes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,UserSideClothVendors.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }



    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.nav_share)
        {
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, "Hey check out my app at: https://play.google.com/store/apps/details?id=com.google.android.apps.plus");
            sendIntent.setType("text/plain");
            startActivity(sendIntent);
        }
        else
            if (id == R.id.nav_send)
            {
           Logout();
        }

            else if(item.getItemId()==R.id.Contact)
            {
                Intent intent=new Intent(Intent.ACTION_SEND);
                intent.setData(Uri.parse("Mailto:"));
                String [] to={"ashutoshrai181094@gmail.com"};
                intent.putExtra(Intent.EXTRA_EMAIL,to);
                intent.putExtra(Intent.EXTRA_SUBJECT,"Query related to App");
                intent.setType("text/html");
                startActivity(Intent.createChooser(intent, "Send Email"));
            }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
            imageView.setImageResource(sampleImages[position]);
        }
    };
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.main2,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        if(item.getItemId()==R.id.aboutus)
        {
         Logout();
        }
        if (item.getItemId() == R.id.action_settings) {
            return true;
        }

        else if(item.getItemId()==R.id.share)
        {
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            startActivity(shareIntent);
        }

        else
        {

        }

        return super.onOptionsItemSelected(item);
    }

    private void Logout()
    {
       mAuth.signOut();
       Intent intent=new Intent(MainActivity.this,Login.class);
       startActivity(intent);
       finish();
    }

    @Override
    protected void onStart()
    {
        super.onStart();
        FirebaseUser user=FirebaseAuth.getInstance().getCurrentUser();
        if(user==null)
        {
            Intent intent=new Intent(MainActivity.this,Login.class);
            startActivity(intent);
            finish();
        }
        else if(user.getEmail().equals(""))
        {
            Intent intent=new Intent(MainActivity.this,VendorData.class);
            startActivity(intent);
            finish();
        }
        else
        {

        }
    }
    public void verifyPermissions(String[] permissions)
    {
        ActivityCompat.requestPermissions(MainActivity.this,permissions,REQUEST_CODE);
    }
    public boolean checkPermissions(String[] permission)
    {
        int permissionRequest=ActivityCompat.checkSelfPermission(MainActivity.this,permission[0]);
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
        UniversalImageLoader universalImageLoader=new UniversalImageLoader(MainActivity.this);
        ImageLoader.getInstance().init(universalImageLoader.getConfig());
    }




}
///