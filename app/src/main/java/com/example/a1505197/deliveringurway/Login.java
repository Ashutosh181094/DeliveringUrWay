package com.example.a1505197.deliveringurway;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity
{
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    EditText etemail,etpassword;
    EditText forgotpasswordEmail;
    String email,password;
    Button SignIn,SignUp,bForgotPassword;
    TextView tvForgotPassword,LoginVendor;
    ProgressBar progressBar;
    CardView cardView;
    private MorphAnimation morphAnimationLogin;

    private static final String TAG = "Login";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth=FirebaseAuth.getInstance();
        forgotpasswordEmail=findViewById(R.id.etForgotPasswordemail);
        View loginContainer = findViewById(R.id.form_btn);
        TextView Forgotpass = (TextView) findViewById(R.id.button_inside_group);
        ViewGroup loginViews = (ViewGroup) findViewById(R.id.login_views);
        cardView=findViewById(R.id.form_btn);
        final FrameLayout rootView = (FrameLayout) findViewById(R.id.root_view);
        SignIn= (Button) findViewById(R.id.btn_login);
        SignUp= (Button) findViewById(R.id.btn_register);
        etemail= (EditText) findViewById(R.id.etEmail);
        etpassword= (EditText) findViewById(R.id.etPassword);
        bForgotPassword=  findViewById(R.id.ok);
        LoginVendor=findViewById(R.id.LoginAsVendor);
        setupsignin();
        initProgressBar();
        LoginVendor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Login.this,VendorLogin.class);
                startActivity(intent);
            }
        });
        morphAnimationLogin = new MorphAnimation(loginContainer, rootView, loginViews);
        rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cardView.setCardBackgroundColor(Color.TRANSPARENT);

                morphAnimationLogin.morphIntoButton();
            }
        });
        Forgotpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!morphAnimationLogin.isPressed())
                {
                    cardView.setCardBackgroundColor(getResources().getColor(R.color.White));
                    morphAnimationLogin.morphIntoForm();
                } else {
                    cardView.setCardBackgroundColor(Color.TRANSPARENT);
                    morphAnimationLogin.morphIntoButton();

                }
            }
        });
        bForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {

                email=forgotpasswordEmail.getText().toString();
                if(email.equals(""))
                {
                    Toast.makeText(getApplicationContext(),"Fill the field",Toast.LENGTH_SHORT).show();
                }
                else {
                    morphAnimationLogin.morphIntoButton();
                    mAuth.sendPasswordResetEmail(email)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(getApplicationContext(), "Email sent.", Toast.LENGTH_LONG).show();
                                    } else {
                                        Toast.makeText(getApplicationContext(), "Email not sent", Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                }
            }
        });
        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Login.this,Register.class);
                startActivity(intent);
            }
        });

    }

    public void setupsignin()
    {
        SignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email=etemail.getText().toString();
                password=etpassword.getText().toString();

                showProgressBar();
                if(isStringNull(email)||isStringNull(password))
                {

                    Toast.makeText(Login.this,"Fill in all the fields",Toast.LENGTH_LONG).show();
                }

                    else

                {

                    mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task)
                        {
                            FirebaseUser user=FirebaseAuth.getInstance().getCurrentUser();
                            if(task.isSuccessful()&&user.isEmailVerified())
                            {

                                Toast.makeText(Login.this,"Login Successful",Toast.LENGTH_LONG).show();

                                 hideProgressBar();
                                Intent intent=new Intent(Login.this,MainActivity.class);
                                startActivity(intent);
                                finish();

                                //if the authentication is successfull
                            }
                            else
                            {


                                    Toast.makeText(Login.this,"Login Failed",Toast.LENGTH_LONG).show();
                                FirebaseAuth.getInstance().signOut();


                                //if the authentication is not sucessful
                                hideProgressBar();
                            }
                        }
                    });
                }
            }
        });


    }




    public boolean isStringNull(String string)
    {
        if(string.equals(""))
            return true;
        else
            return false;
    }
    private void showProgressBar(){
        progressBar.setVisibility(View.VISIBLE);

    }

    private void hideProgressBar(){
        if(progressBar.getVisibility() == View.VISIBLE){
            progressBar.setVisibility(View.INVISIBLE);
        }
    }
    private void initProgressBar(){
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setVisibility(View.INVISIBLE);
    }


    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser user=FirebaseAuth.getInstance().getCurrentUser();
        if(user==null)
        {
       // not signed in
        }
        else
        {
            Intent intent=new Intent(Login.this,MainActivity.class);
            startActivity(intent);
            finish();
          // signed in
        }

    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setMessage("Do you want to Exit?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //if user pressed "yes", then he is allowed to exit from application
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //if user select "No", just cancel this dialog and continue with app
                dialog.cancel();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }

}
///////////////