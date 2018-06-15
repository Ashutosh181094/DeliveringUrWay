package com.example.a1505197.deliveringurway;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by 1505197 on 2/7/2018.
 */

public class ForgotPasswordDialog extends DialogFragment
{
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    EditText forgotPasswordEmail;
    Button sendForgotPassword;
    String email;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.customdialog,null);
        forgotPasswordEmail=(EditText) view.findViewById(R.id.etForgotPasswordemail);
        sendForgotPassword=(Button)view.findViewById(R.id.btnSendPassword);
        mAuth=FirebaseAuth.getInstance();
        sendForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email=forgotPasswordEmail.getText().toString();
                mAuth.sendPasswordResetEmail(email)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(getActivity(),"Email sent",Toast.LENGTH_LONG).show();
                                }
                                else
                                {
                                    Toast.makeText(getActivity(),"Email not sent",Toast.LENGTH_LONG).show();
                                }
                            }
                        });
            }
        });
        return view;
    }
    @Override
    public void onStart() {
        super.onStart();

    }
    @Override
    public void onStop() {
        super.onStop();
    }
}
//////////////