package com.example.a1505197.deliveringurway;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;
import com.stepstone.apprating.AppRatingDialog;
import com.stepstone.apprating.listener.RatingDialogListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AboutProduct extends AppCompatActivity implements RatingDialogListener
{
ImageView imageView;
CollapsingToolbarLayout collapsingToolbarLayout;
FloatingActionButton btnRating;
RatingBar ratingBar;
DatabaseReference databaseReferenceRatings;
String productName,phoneNumber;
FirebaseUser username;
CommentAdapter commentAdapter;
ArrayList<Rating> ratings;
RecyclerView recyclerView;
String Comment;
TextView Description,numofComment;
int Rating,Ratingsum=0;
int a[]=new int[5];
int ratingone=0,ratingtwo=0,ratingthree=0,ratingfour=0,ratingfive=0;
    ArrayList<FoodProductDescription> userSideFoodProductDescription;
    ArrayList<ClothProductDescription> clothProductDescriptions;
    ArrayList<RentalProductDescription> rentalProductDescriptions;
    DatabaseReference Vendortotaldata;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_product);
        btnRating=findViewById(R.id.btn_rating);
        Intent intent=getIntent();
        ratingBar=findViewById(R.id.ratingBar);
        Description=findViewById(R.id.product_description);
        imageView=findViewById(R.id.itemImage);
        ratings=new ArrayList<>();
        numofComment=findViewById(R.id.numofComment);
        productName=intent.getStringExtra("productName");
        phoneNumber=intent.getStringExtra("phoneNumber");
        Toast.makeText(getApplicationContext(),""+productName+""+phoneNumber,Toast.LENGTH_SHORT).show();
        username= FirebaseAuth.getInstance().getCurrentUser();
        recyclerView=findViewById(R.id.recyclerViewComments);
        userSideFoodProductDescription =new ArrayList<>();
        clothProductDescriptions=new ArrayList<>();
        rentalProductDescriptions=new ArrayList<>();
       final String s[]=new String[5];


        databaseReferenceRatings=FirebaseDatabase.getInstance().getReference().child("+91"+phoneNumber).child(productName);
        databaseReferenceRatings.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists())
                {
                 ratings.clear();
                 Ratingsum=0;
                 ratingone=0;
                 ratingtwo=0;
                 ratingthree=0;
                 ratingfour=0;
                 ratingfive=0;
                    for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren())
                    {
                        Rating rating = dataSnapshot1.getValue(Rating.class);
                        ratings.add(rating);

                        if(rating.rating==1)
                        {
                            ratingone++;
                        }
                        else
                            if(rating.rating==2)
                            {
                                ratingtwo++;
                            }
                            else
                            if(rating.rating==3)
                            {
                                ratingthree++;
                            }
                            else
                            if(rating.rating==4)
                            {
                                ratingfour++;
                            }
                            else
                            {
                               ratingfive++;
                            }
                            if(ratingone!=0)
                            {
                                s[0]="ONE";
                            }
                            else
                            {
                                s[0]="";
                            }
                            if(ratingtwo!=0)
                            {
                                s[1]="TWO";
                            }
                            else {
                                s[1] = "";
                            }
                            if(ratingthree!=0)
                            {
                                s[2]="THREE";
                            }
                            else
                            {
                                s[2]="";
                            }
                            if(ratingfour!=0)
                            {
                                s[3]="FOUR";
                            }
                            else
                            {
                                s[3]="";
                            }

                            if(ratingfive!=0)
                            {
                                s[4]="FIVE";
                            }
                            else
                            {
                                s[4]="";
                            }







                        Ratingsum=Ratingsum+rating.rating;
                    }
                    a[0]=ratingone;
                    a[1]=ratingtwo;
                    a[2]=ratingthree;
                    a[3]=ratingfour;
                    a[4]=ratingfive;
                    List<PieEntry> p=new ArrayList<>();
                    for(int i=0;i<5;i++)
                    {
                        p.add(new PieEntry(a[i],s[i]));
                    }
                    PieDataSet pieDataSet=new PieDataSet(p,"Rating For Products");
                    pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
                    PieData pieData=new PieData(pieDataSet);
                    PieChart chart=findViewById(R.id.ratingChart);
                    chart.setCenterText("Ratings");
                    chart.setData(pieData);
                    chart.animateY(1000);
                    chart.invalidate();

                    numofComment.setText("("+ratings.size()+")");

                    CommentAdapter commentsAdapter=new CommentAdapter(AboutProduct.this,ratings);
                    recyclerView.setAdapter(commentsAdapter);
                    recyclerView.setHasFixedSize(true);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    ratingBar.setRating(Ratingsum/(ratings.size()));
                    commentsAdapter.notifyDataSetChanged();
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
         VendorType vendorType=new VendorType();
         String Type=vendorType.getType();


        btnRating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showRatingDialog();
            }
        });

        Vendortotaldata= FirebaseDatabase.getInstance().getReference("productinfo").child(""+Type).child("+91"+phoneNumber).child(productName);
        Vendortotaldata.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                VendorType vendorType=new VendorType();
                String Type=vendorType.getType();

                if (dataSnapshot.exists())
                {
               if(Type.equals("Food"))
               {
                   userSideFoodProductDescription.clear();

                   FoodProductDescription foodProductDescription = dataSnapshot.getValue(FoodProductDescription.class);
                   userSideFoodProductDescription.add(foodProductDescription);
                   Description.setText(userSideFoodProductDescription.get(0).description);
                   Picasso.with(getApplicationContext())
                           .load(userSideFoodProductDescription.get(0).image_url)
                           .fit()
                           .centerCrop()
                           .into(imageView);
               }
               else
                   if(Type.equals("Clothing"))
                   {
                       clothProductDescriptions.clear();

                       ClothProductDescription clothProductDescription = dataSnapshot.getValue(ClothProductDescription.class);
                       clothProductDescriptions.add(clothProductDescription);
                       Description.setText(clothProductDescriptions.get(0).size_available+"\n"+clothProductDescriptions.get(0).description);
                       Picasso.with(getApplicationContext())
                               .load(clothProductDescriptions.get(0).image_url)
                               .fit()
                               .centerCrop()
                               .into(imageView);
                   }
                   else
                   {
                       rentalProductDescriptions.clear();

                       RentalProductDescription rentalProductDescription = dataSnapshot.getValue(RentalProductDescription.class);
                       rentalProductDescriptions.add(rentalProductDescription);
                       Description.setText("Cost/Day->"+rentalProductDescriptions.get(0).cost_per_day+"\nCost/Hour->"+rentalProductDescriptions.get(0).cost_per_hour);
                       Picasso.with(getApplicationContext())
                               .load(rentalProductDescriptions.get(0).image_url)
                               .fit()
                               .centerCrop()
                               .into(imageView);
                   }
                }
            }


            @Override
            public void onCancelled(DatabaseError databaseError)
            {
                Toast.makeText(getApplicationContext(),"Database error",Toast.LENGTH_LONG).show();

            }
        });
    }

    private void showRatingDialog()
    {
        new AppRatingDialog.Builder()
                .setPositiveButtonText("Submit")
                .setNegativeButtonText("Cancel")
                .setNoteDescriptions(Arrays.asList("Very Bad","Not Good","Quite Ok","Very Good","Excellent"))
                .setDefaultRating(1)
                .setTitle("Rate this Product")
                .setDescription("Please Select some stars and give your feedback")
                .setTitleTextColor(R.color.colorPrimary)
                .setDescriptionTextColor(R.color.colorPrimary)
                .setHint("Please write your comment here....")
                .setHintTextColor(R.color.DarkGrey)
                .setCommentBackgroundColor(R.color.lightGrey)
                .setWindowAnimation(R.style.RatingDialogFadeAnim)
                .create(AboutProduct.this)
                .show();
    }

    @Override
    public void onPositiveButtonClicked(int ratingValue, String comment)
    {
        Rating=ratingValue;
        Comment=comment;
        UserImage userImage=new UserImage();
        String image_url=userImage.getImage_url();
        Toast.makeText(this, ""+image_url, Toast.LENGTH_SHORT).show();
        databaseReferenceRatings= FirebaseDatabase.getInstance().getReference().child("+91"+phoneNumber).child(productName);
        Rating rating=new Rating(username.getEmail(),Comment,Rating);
        databaseReferenceRatings.push().setValue(rating);
        Comment="";
    }

    @Override
    public void onNegativeButtonClicked() {

    }

}
////////////