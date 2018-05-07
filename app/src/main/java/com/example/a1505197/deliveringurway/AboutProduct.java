package com.example.a1505197.deliveringurway;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.stepstone.apprating.AppRatingDialog;
import com.stepstone.apprating.listener.RatingDialogListener;

import java.util.Arrays;

public class AboutProduct extends AppCompatActivity implements RatingDialogListener
{
ImageView imageView;
CollapsingToolbarLayout collapsingToolbarLayout;
FloatingActionButton btnRating;
RatingBar ratingBar;
DatabaseReference databaseReferenceRatings;
String productName,phoneNumber;
FirebaseUser username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_product);
        btnRating=findViewById(R.id.btn_rating);
        Intent intent=getIntent();
        productName=intent.getStringExtra("productName");
        phoneNumber=intent.getStringExtra("phoneNumber");
        username= FirebaseAuth.getInstance().getCurrentUser();
        btnRating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showRatingDialog();
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
                .setHintTextColor(R.color.colorAccent)
                .setCommentBackgroundColor(R.color.colorPrimaryDark)
                .setWindowAnimation(R.style.RatingDialogFadeAnim)
                .create(AboutProduct.this)
                .show();
    }

    @Override
    public void onPositiveButtonClicked(int ratingValue, String comment) {
        databaseReferenceRatings= FirebaseDatabase.getInstance().getReference().child("+91"+phoneNumber).child(productName);
       Rating rating=new Rating(username.getEmail(),comment,ratingValue);
       databaseReferenceRatings.push().setValue(rating);
    }

    @Override
    public void onNegativeButtonClicked() {

    }
}
