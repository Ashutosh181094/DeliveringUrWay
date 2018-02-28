package com.example.a1505197.deliveringurway;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class VendorInfo5 extends AppCompatActivity {

    EditText password;
    EditText confirmPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vendorregistration5);

        password=(EditText)findViewById(R.id.EditTextPassword);
        confirmPassword=(EditText)findViewById(R.id.EditTextConfirPassword);
    }
}
