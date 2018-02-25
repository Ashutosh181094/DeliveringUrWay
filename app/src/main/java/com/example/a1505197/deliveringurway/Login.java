package com.example.a1505197.deliveringurway;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.valdesekamdem.library.mdtoast.MDToast;

public class Login extends AppCompatActivity
{
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    EditText etemail,etpassword;
    String email,password;
    Button SignIn,SignUp;
    Button tvForgotPassword;
    ProgressBar progressBar;
    private static final String TAG = "Login";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth=FirebaseAuth.getInstance();
        SignIn= (Button) findViewById(R.id.btn_login);
        SignUp= (Button) findViewById(R.id.btn_register);
        etemail= (EditText) findViewById(R.id.etEmail);
        etpassword= (EditText) findViewById(R.id.etPassword);
        tvForgotPassword= (Button) findViewById(R.id.tvForgotPassword);
        setupsignin();
        initProgressBar();
        tvForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
               ForgotPasswordDialog forgotPasswordDialog=new ForgotPasswordDialog();
                forgotPasswordDialog.show(getFragmentManager(),"Forgot Password");
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
                if(isStringNull(email)&&isStringNull(password))
                {
                    Toast.makeText(Login.this,"Fill in all the fields",Toast.LENGTH_LONG).show();
                }
                else
                {
                    mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful())
                            {
                                 Toast.makeText(Login.this,"Login Successful",Toast.LENGTH_LONG).show();

                                 hideProgressBar();
                                Intent intent=new Intent(Login.this,MainActivity.class);
                                startActivity(intent);


                                //if the authentication is successfull
                            }
                            else
                            {
                                Toast.makeText(Login.this,"Login Failed",Toast.LENGTH_LONG).show();
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

}
