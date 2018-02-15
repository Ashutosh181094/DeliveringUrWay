package com.example.a1505197.deliveringurway;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

public class MainActivity extends AppCompatActivity {
    CarouselView carouselView;
    Toolbar toolbar;

    int[] sampleImages = {R.drawable.hut, R.drawable.hut, R.drawable.hut, R.drawable.hut, R.drawable.hut};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        carouselView = (CarouselView) findViewById(R.id.carouselView);
        toolbar = (Toolbar) findViewById(R.id.customToolbar);
        carouselView.setPageCount(sampleImages.length);

        carouselView.setImageListener(imageListener);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("DUR WAY");

        getSupportActionBar().setElevation(10f);


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
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        if(item.getItemId()==R.id.aboutus)
        {

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




}



