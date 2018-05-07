package com.example.a1505197.deliveringurway;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;

import com.google.firebase.database.DatabaseReference;
import com.stepstone.apprating.AppRatingDialog;
import com.stepstone.apprating.listener.RatingDialogListener;

import java.util.Arrays;

public class AboutProduct extends AppCompatActivity implements RatingDialogListener
{
ImageView imageView;
CollapsingToolbarLayout collapsingToolbarLayout;
FloatingActionButton btnRating;
RatingBar ratingBar;
DatabaseReference rating;
DatabaseReference comment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_product);
        btnRating=findViewById(R.id.btn_rating);
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
    public void onPositiveButtonClicked(int i, String s) {

    }

    @Override
    public void onNegativeButtonClicked() {

    }
}
