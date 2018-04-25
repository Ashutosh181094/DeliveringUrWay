package com.example.a1505197.deliveringurway;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import de.hdodenhof.circleimageview.CircleImageView;

public class VendorInfo2 extends AppCompatActivity {
   EditText nameOfBusiness;
   EditText ownerOfBusiness;
   String nameOfB="";
   String OwnerOfB="";
    CircleImageView Arrow2;
    Spinner type;
    String categories[]={"Food","Clothing"};
    String Type="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vendorregistration2);
        Arrow2=(CircleImageView) findViewById(R.id.arrow2);
        nameOfBusiness=(EditText) findViewById(R.id.nameOfBusiness);
        ownerOfBusiness=(EditText) findViewById(R.id.ownerOfBusiness);
        type=findViewById(R.id.type);
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);


        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        type.setAdapter(dataAdapter);
       type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           @Override
           public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               if(position==0)
               {
                   Type=Type+"Food";
               }
               else
               {
                   Type=Type+"Clothing";
               }
           }

           @Override
           public void onNothingSelected(AdapterView<?> parent) {

           }
       });
        Arrow2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nameOfB = nameOfBusiness.getText().toString();
                OwnerOfB = ownerOfBusiness.getText().toString();
                if (OwnerOfB.equals("") || nameOfB.equals("")) {
                    Toast.makeText(getApplicationContext(), "Please fill all fields", Toast.LENGTH_SHORT).show();
                } else
                {

                Intent intent = new Intent(VendorInfo2.this, VendorInfo3.class);
                    intent.putExtra("nameOfB",nameOfB);
                    intent.putExtra("OwnerOfB",OwnerOfB);
                    intent.putExtra("type",Type);
                    startActivity(intent);
            }

            }
        });
    }
}
//