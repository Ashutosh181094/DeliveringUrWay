package com.example.a1505197.deliveringurway;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class VendorLogin extends AppCompatActivity {
    EditText phoneNumber;
    EditText OTP;
    String sphoneNumber;
    Button sendOtp,Verify,Signup;
    DatabaseReference registeredvendors;
    ArrayList<RegisteredVendors> listregisteredVendors;
    PhoneAuthProvider.OnVerificationStateChangedCallbacks mCall;
    ProgressBar progressBar;
    FirebaseAuth mAuth;
    private static final String TAG = "VendorLogin";
    String  mVerificationId;
    PhoneAuthProvider.ForceResendingToken mResendToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendor_login);
        phoneNumber=findViewById(R.id.etPhoneNumber);
        sendOtp=findViewById(R.id.btn_sendOTP);
        OTP=findViewById(R.id.etOTP);
        Verify=findViewById(R.id.btn_verify);
        Signup=findViewById(R.id.btn_register);
        progressBar=findViewById(R.id.progressBar);
        mAuth=FirebaseAuth.getInstance();
        hideProgressBar();
        listregisteredVendors=new ArrayList<>();

        sendOtp.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {

                sphoneNumber = "+91"+phoneNumber.getText().toString();
                registeredvendors= FirebaseDatabase.getInstance().getReference("registered");
                registeredvendors.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                                RegisteredVendors registeredVendors = dataSnapshot1.getValue(RegisteredVendors.class);
                                if (registeredVendors.phone_number.equals(sphoneNumber)) {
                                    listregisteredVendors.add(registeredVendors);
                                }
                            }
                            if (sphoneNumber.equals("")) {
                                showError();
                            }
                            else
                            if(listregisteredVendors.size()==0)
                            {
                                Toast.makeText(VendorLogin.this, "You Have not registered yet", Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                showProgressBar();
                                phoneNumber.setVisibility(View.GONE);
                                sendOtp.setVisibility(View.GONE);
                                Signup.setVisibility(View.GONE);
                                Verify.setVisibility(View.VISIBLE);
                                OTP.setVisibility(View.VISIBLE);

                                PhoneAuthProvider.getInstance().verifyPhoneNumber(
                                        sphoneNumber,
                                        60,
                                        TimeUnit.SECONDS,
                                        VendorLogin.this,
                                        mCall
                                );
                            }

                        }
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

            }
        });
        Verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                PhoneAuthCredential credential=PhoneAuthProvider.getCredential(mVerificationId,OTP.getText().toString());
                signInWithPhoneAuthCredential(credential);
            }
        });
        mCall=new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
                hideProgressBar();
                signInWithPhoneAuthCredential(phoneAuthCredential);
            }

            @Override
            public void onVerificationFailed(FirebaseException e) {
                Toast.makeText(VendorLogin.this,"Error on verification failed"+e.getMessage(),Toast.LENGTH_LONG).show();
                hideProgressBar();
                Log.d(TAG, "onVerificationFailed: "+e.getMessage());

            }
            @Override
            public void onCodeSent(String verificationId,
                                   PhoneAuthProvider.ForceResendingToken token) {
                // The SMS verification code has been sent to the provided phone number, we
                // now need to ask the user to enter the code and then construct a credential
                // by combining the code with a verification ID.
                Log.d(TAG, "onCodeSent:" + verificationId);

                // Save verification ID and resending token so we can use them later
                mVerificationId = verificationId;
                mResendToken = token;

                // ...
            }

        };
        Signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),VendorSelectCity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    private void showError()
    {
        phoneNumber.setError("Fill the field");
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Intent intent=new Intent(VendorLogin.this,VendorData.class);
                            startActivity(intent);
                            finish();

                            FirebaseUser user = task.getResult().getUser();
                            // ...
                        } else {
                            Toast.makeText(VendorLogin.this,"Error in signing in",Toast.LENGTH_LONG).show();
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                Toast.makeText(VendorLogin.this,"Verification code Entered was invalid",Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                });
    }
    //
    private void showProgressBar()
    {
        progressBar.setVisibility(View.VISIBLE);

    }

    private void hideProgressBar()
    {
        if(progressBar.getVisibility() == View.VISIBLE)
        {
            progressBar.setVisibility(View.INVISIBLE);
        }
    }
}
//
////////////////