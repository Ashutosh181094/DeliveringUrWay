package com.example.a1505197.deliveringurway;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
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
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;
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
    DatabaseReference userData,userDataFetch;
    TextView name;
    FirebaseUser user;
    StorageReference storeUserPhoto;


    private View navHeader;
    int[] sampleImages = {R.drawable.hut, R.drawable.find_your_favourite, R.drawable.explore, R.drawable.business_online, R.drawable.pink};
     String userName="";
     NavigationView navigation_header;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth=FirebaseAuth.getInstance();
        tvFood=findViewById(R.id.tvFood);
        tvClothes=findViewById(R.id.tvClothing);
        userName=sendMessage.message;
        Toast.makeText(getApplicationContext(),""+userName,Toast.LENGTH_SHORT).show();
        userData= FirebaseDatabase.getInstance().getReference("users");
        user=mAuth.getCurrentUser();
        if(userName!=null) {
            UserInfo userInfo = new UserInfo(userName, "https://firebasestorage.googleapis.com/v0/b/deliveringurway.appspot.com/o/user.png?alt=media&token=abc01b91-0d12-4235-afc5-1c70abf7402b", user.getEmail());
            userData.child(user.getUid()).setValue(userInfo);
        }


        navigation_header=findViewById(R.id.nav_view);


        navHeader = navigation_header.getHeaderView(0);
        userCircleImage=navHeader.findViewById(R.id.imageViewUser);
        name=navHeader.findViewById(R.id.textViewName);




        userCircleImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
               final CharSequence[] item={"Camera","Gallery","Cancel"};
                AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Add Users Image");
                builder.setItems(item, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                     if(item[which].equals("Camera"))
                     {
                         Intent cameraIntent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                         startActivityForResult(cameraIntent,Init.CAMERA_REQUEST_CODE);
                     }
                     else
                         if(item[which].equals("Gallery"))
                         {
                             Intent intent=new Intent(Intent.ACTION_GET_CONTENT);
                             intent.setType("image/*");
                             startActivityForResult(intent,Init.PICKFILE_REQUEST_CODE);
                         }
                         else
                             if(item[which].equals("Cancel"))
                             {
                                 dialog.dismiss();
                             }
                    }
                });
                builder.show();
            }
        });




        carouselView = (CarouselView) findViewById(R.id.carouselView);
        carouselView.setPageCount(sampleImages.length);
        carouselView.setImageListener(imageListener);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");

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
        storephoto();
    }

    private void storephoto()
    {

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
        userDataFetch=FirebaseDatabase.getInstance().getReference("users").child(user.getUid());
        userDataFetch.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists())
                {
                    UserInfo userInfo=dataSnapshot.getValue(UserInfo.class);
                    Picasso.with(MainActivity.this)
                            .load(userInfo.image_url)
                            .fit()
                            .centerCrop()
                            .into(userCircleImage);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==Init.CAMERA_REQUEST_CODE&&resultCode== Activity.RESULT_OK)
        {
            Bitmap bitmap=(Bitmap)data.getExtras().get("data");
            userCircleImage.setImageBitmap(bitmap);
        }
        if (requestCode==Init.PICKFILE_REQUEST_CODE && resultCode == Activity.RESULT_OK)
        {
            Uri selectedImageUri=data.getData();
           userCircleImage.setImageURI(selectedImageUri);
        }
        storeUserPhoto= FirebaseStorage.getInstance().getReference(user.getEmail());
        userCircleImage.setDrawingCacheEnabled(true);
        userCircleImage.buildDrawingCache();
        Bitmap bitmap = userCircleImage.getDrawingCache();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] data1 = baos.toByteArray();


        UploadTask uploadTask = storeUserPhoto.putBytes(data1);
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle unsuccessful uploads
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                // taskSnapshot.getMetadata() contains file metadata such as size, content-type, and download URL.
                Uri downloadUrl = taskSnapshot.getDownloadUrl();
                userData.child(user.getUid()).child("image_url").setValue(taskSnapshot.getDownloadUrl().toString());
            }
        });
    }



}




///