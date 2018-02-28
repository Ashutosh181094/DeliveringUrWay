package com.example.a1505197.deliveringurway;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import de.hdodenhof.circleimageview.CircleImageView;

public class VendorInfo4 extends AppCompatActivity {
    RadioGroup rg;
    RadioButton yes,no,cond,Rbtn;
    Integer r;
    CircleImageView arrow3;
    VendorInformation vendorInformation;
    DatabaseReference vendordata;
    String nameOFB,OwnerOfB,Address,PhoneNumber;
    int nightdelivery,paytmAccepted;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vendorregistration4);
        rg=(RadioGroup)findViewById(R.id.radiogroupDelivery);
        no=(RadioButton)findViewById(R.id.radioButtonNO);
        yes=(RadioButton)findViewById(R.id.radioButtonYES);
        cond=(RadioButton)findViewById(R.id.radioButtonCOND);




//        int n=rg.getCheckedRadioButtonId();
//        if(n==1){
//            Toast.makeText(getApplicationContext(),"1",Toast.LENGTH_SHORT).show();

//        }ff

        arrow3=(CircleImageView) findViewById(R.id.arrow3);
        // onclicklistener

        arrow3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent getintent=getIntent();
                nameOFB=getintent.getStringExtra("nameOfB");
                OwnerOfB=getintent.getStringExtra("OwnerOfB");
                Address=getintent.getStringExtra("Address");
                PhoneNumber=getintent.getStringExtra("PhoneNumber");
                nightdelivery=getintent.getIntExtra("nightDelivery",0);
                paytmAccepted=getintent.getIntExtra("paytmAccepted",0);
                vendordata= FirebaseDatabase.getInstance().getReference("vendors");
                VendorInformation vendorInformation=new VendorInformation(nightdelivery,paytmAccepted,nameOFB,OwnerOfB,Address,PhoneNumber);
                String id=vendordata.push().getKey();
                vendordata.child(id).setValue(vendorInformation);
                Toast.makeText(VendorInfo4.this,nameOFB,Toast.LENGTH_LONG).show();
                Toast.makeText(VendorInfo4.this,OwnerOfB,Toast.LENGTH_LONG).show();
                Toast.makeText(VendorInfo4.this,Address,Toast.LENGTH_LONG).show();
                Toast.makeText(VendorInfo4.this,PhoneNumber,Toast.LENGTH_LONG).show();




            }
        });








    }
    public void rbClick(View v){
        int radioButtonId=rg.getCheckedRadioButtonId();
        Rbtn=(RadioButton)findViewById(radioButtonId);

        String radioText=Rbtn.getText().toString();
        if(radioText.equals("YES, but conditionally")){
            //Toast.makeText(getApplicationContext(),"THIS IS  "+Rbtn.getText(),Toast.LENGTH_SHORT).show();
            showChangeLangDialog();

        }
    }

    public void showChangeLangDialog() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.custom_dialog, null);
        dialogBuilder.setView(dialogView);

//        final EditText minAmount = (EditText) dialogView.findViewById(R.id.edit1);
//        final EditText distance = (EditText) dialogView.findViewById(R.id.edit2);
//        final EditText chargesOtherwise = (EditText) dialogView.findViewById(R.id.edit3);

        dialogBuilder.setTitle("Yes, but Conditionally");
        dialogBuilder.setMessage("Conditions");
        dialogBuilder.setPositiveButton("Done", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                //do something with edt.getText().toString();
//                if(edt1.getText().toString().equals("")||edt2.getText().toString().equals("")||edt3.getText().toString().equals("")){
//                    Toast.makeText(getApplicationContext(),"Please enter all values",Toast.LENGTH_SHORT).show();
//
//
//                }
            }
        });
        dialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                //pass
            }
        });
        final AlertDialog b = dialogBuilder.create();
        b.show();
        b.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final EditText minAmount = (EditText) dialogView.findViewById(R.id.edit1);
                final EditText distance = (EditText) dialogView.findViewById(R.id.edit2);
                final EditText chargesOtherwise = (EditText) dialogView.findViewById(R.id.edit3);


                if(minAmount.getText().equals("")||distance.getText().toString().equals("")||chargesOtherwise.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(),"Please enter all values",Toast.LENGTH_SHORT).show();

                }
                else{



                    b.dismiss();
                }
            }
        });

    }

}
