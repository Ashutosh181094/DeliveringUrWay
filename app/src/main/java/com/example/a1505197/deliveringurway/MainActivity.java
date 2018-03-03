package com.example.a1505197.deliveringurway;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    CarouselView carouselView;
    Toolbar toolbar;
    FirebaseAuth mAuth;

    int[] sampleImages = {R.drawable.hut, R.drawable.hut, R.drawable.hut, R.drawable.hut, R.drawable.hut};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth=FirebaseAuth.getInstance();
        carouselView = (CarouselView) findViewById(R.id.carouselView);
        toolbar = (Toolbar) findViewById(R.id.customToolbar);
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

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {
            Toast.makeText(getApplicationContext(),"ABHI KAAM CHAALU HAI",Toast.LENGTH_LONG).show();

        } else if (id == R.id.nav_slideshow) {
            Toast.makeText(getApplicationContext(),"BOLA NA ABHI KAAM CHAALU HAI",Toast.LENGTH_LONG).show();

        } else if (id == R.id.nav_manage) {
            Toast.makeText(getApplicationContext(),"SAMJH NAHI AATA KI ABHI KAAM CHAALU HAI",Toast.LENGTH_LONG).show();

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {
           Logout();
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
            Toast.makeText(MainActivity.this,"Its a vendor",Toast.LENGTH_LONG).show();
            Intent intent=new Intent(MainActivity.this,CustomerData.class);
            startActivity(intent);
            finish();
        }
        else
        {
            Toast.makeText(MainActivity.this,"Its a user",Toast.LENGTH_LONG).show();

        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
//

//
