package com.example.a1505197.deliveringurway;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Register extends AppCompatActivity
{
    FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    EditText etName,etEmail,etPassword,etConfirmPassword;
    Button buttonRegister;
    String email,password,confirmpassword;
    ProgressBar progressBar;
    TextView tvRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth=FirebaseAuth.getInstance();
        etName= (EditText) findViewById(R.id.etname);
        etEmail= (EditText) findViewById(R.id.etEmail);
        etPassword= (EditText) findViewById(R.id.etPassword);
        etConfirmPassword= (EditText) findViewById(R.id.etConfirmPassword);
        buttonRegister= (Button) findViewById(R.id.btn_register);
        tvRegister=(TextView) findViewById(R.id.textViewRegister);
        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Register.this,Vendorinfo1.class);
                startActivity(intent);
            }
        });
        initProgressBar();
        setUpFireBase();
        createNewUser();

    }
    public void setUpFireBase()
    {
        mAuthListener=new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user=firebaseAuth.getCurrentUser();
                if(user!=null)
                {
                    //signed in
                }
                else
                {
                    //signed out
                }
            }
        };
    }
    public  void createNewUser()
    {
       buttonRegister.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view)
           {
               email=etEmail.getText().toString();
               password=etPassword.getText().toString();
               confirmpassword=etConfirmPassword.getText().toString();
               showProgressBar();
               if (isStringNull(email)&&isStringNull(password)&&isStringNull(confirmpassword))
               {
                   Toast.makeText(Register.this,"Fill in all the fields",Toast.LENGTH_LONG).show();
               }
               else
                   if (!isStringEqual(password,confirmpassword))
                   {
                       Toast.makeText(Register.this,"Password and confirm password do not match",Toast.LENGTH_LONG).show();
                       etConfirmPassword.setText("");
                       etPassword.setText("");
                   }
               else
               {
                   mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                       @Override
                       public void onComplete(@NonNull Task<AuthResult> task)
                       {
                           if (task.isSuccessful())
                           {
                               sendVerificationEmail();
                            Toast.makeText(Register.this,"Varification Email sent",Toast.LENGTH_LONG).show();
                              hideProgressBar();
                           }
                           else
                           {

                           }

                       }
                   });
               }
           }
       });

    }
    public void sendVerificationEmail() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if (user != null) {
            user.sendEmailVerification()
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful())
                            {

                            }
                            else{
                                Toast.makeText(Register.this, "couldn't send email", Toast.LENGTH_SHORT).show();

                            }
                        }
                    });
        }

    }
    public boolean isStringEqual(String password,String Confirmpassword)
    {
        if(password.equals(Confirmpassword))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    public boolean isStringNull(String string)
    {
        if(string.equals(""))
            return true;
        else
            return false;
    }
    private void initProgressBar(){
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setVisibility(View.INVISIBLE);
    }
    private void showProgressBar(){
        progressBar.setVisibility(View.VISIBLE);

    }

    private void hideProgressBar(){
        if(progressBar.getVisibility() == View.VISIBLE){
            progressBar.setVisibility(View.INVISIBLE);
        }
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
