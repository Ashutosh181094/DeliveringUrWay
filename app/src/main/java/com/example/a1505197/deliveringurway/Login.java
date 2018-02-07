package com.example.a1505197.deliveringurway;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
    String email,password;
    Button SignIn,SignUp;
    Button tvForgotPassword;
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
       setUpFirebase();
        setupsignin();
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
    public void setUpFirebase()
    {

        mAuthListener=new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user=firebaseAuth.getCurrentUser();
                if(user!=null)
                {
                    //user is signed in
                }
                else
                {

                    //user is signed out
                }
            }
        };
    }
    public void setupsignin()
    {
        SignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email=etemail.getText().toString();
                password=etpassword.getText().toString();
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
                                Intent intent=new Intent(Login.this,MainActivity.class);
                                startActivity(intent);


                                //if the authentication is successfull
                            }
                            else
                            {
                                Toast.makeText(Login.this,"Login Failed",Toast.LENGTH_LONG).show();
                                //if the authentication is not sucessful
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

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }
    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }
}
