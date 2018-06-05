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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.stepstone.apprating.AppRatingDialog;
import com.stepstone.apprating.listener.RatingDialogListener;

import java.util.ArrayList;
import java.util.Arrays;

public class AboutProduct extends AppCompatActivity implements RatingDialogListener
{
ImageView imageView;
CollapsingToolbarLayout collapsingToolbarLayout;
FloatingActionButton btnRating;
RatingBar ratingBar;
DatabaseReference databaseReferenceRatings,discriptionreference;
String productName,phoneNumber;
FirebaseUser username;
CommentAdapter commentAdapter;
ArrayList<Rating> ratings;
RecyclerView recyclerView;
String Comment;
TextView Description;
int Rating,Ratingsum=0;
    ArrayList<ProductDescription> userSideProductDescription;
    DatabaseReference Vendortotaldata;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_product);
        btnRating=findViewById(R.id.btn_rating);
        Intent intent=getIntent();
        ratingBar=findViewById(R.id.ratingBar);
        Description=findViewById(R.id.product_description);
        ratings=new ArrayList<>();
        productName=intent.getStringExtra("productName");
        phoneNumber=intent.getStringExtra("phoneNumber");
        username= FirebaseAuth.getInstance().getCurrentUser();
        recyclerView=findViewById(R.id.recyclerViewComments);
        userSideProductDescription=new ArrayList<>();


        databaseReferenceRatings=FirebaseDatabase.getInstance().getReference().child("+91"+phoneNumber).child(productName);
        databaseReferenceRatings.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists())
                {
                 ratings.clear();
                    for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren())
                    {
                        Rating rating = dataSnapshot1.getValue(Rating.class);
                        ratings.add(rating);
                        Ratingsum=Ratingsum+rating.rating;
                    }

                    Toast.makeText(getApplicationContext(),""+ratings.size(),Toast.LENGTH_LONG).show();
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



        btnRating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showRatingDialog();
            }
        });

        Vendortotaldata= FirebaseDatabase.getInstance().getReference("productinfo").child("+91"+phoneNumber).child(productName);
        Vendortotaldata.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists())
                {

                    userSideProductDescription.clear();

                        ProductDescription productDescription = dataSnapshot.getValue(ProductDescription.class);
                        userSideProductDescription.add(productDescription);
                        Description.setText(userSideProductDescription.get(0).description);




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
        DatabaseReference userImagedata=FirebaseDatabase.getInstance().getReference("users").child(username.getUid());
        userImagedata.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists())
                {
                    UserInfo userInfo=dataSnapshot.getValue(UserInfo.class);
                    databaseReferenceRatings= FirebaseDatabase.getInstance().getReference().child("+91"+phoneNumber).child(productName);
                    Rating rating=new Rating(username.getEmail(),Comment,userInfo.image_url,Rating);
                    databaseReferenceRatings.push().setValue(rating);
                    Comment="";
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    @Override
    public void onNegativeButtonClicked() {

    }

}
//////