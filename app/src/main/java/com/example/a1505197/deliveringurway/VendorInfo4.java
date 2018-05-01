package com.example.a1505197.deliveringurway;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class VendorInfo4 extends AppCompatActivity {
    RadioGroup rg;
    RadioButton yes,no,cond,Rbtn;
    Integer r;
    Button registerButton;
    VendorInformation vendorInformation;
    DatabaseReference vendordata,deliveryInfo;
    String nameOFB,OwnerOfB,Address,PhoneNumber;
    int nightdelivery,paytmAccepted;
    int addedFlag=0;
    String id;
    String DeliveryInfo="NO";
    DeliveryInformation deliveryInformation;
    EditText minAmount;
    EditText distance;
    EditText chargesOtherwise;
    String Type;
    static  int i=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vendorregistration4);
        rg=(RadioGroup)findViewById(R.id.radiogroupDelivery);
        no=(RadioButton)findViewById(R.id.radioButtonNO);
        yes=(RadioButton)findViewById(R.id.radioButtonYES);
        cond=(RadioButton)findViewById(R.id.radioButtonCOND);






        registerButton=(Button) findViewById(R.id.arrow3);
        // onclicklistener

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent getintent=getIntent();
                nameOFB=getintent.getStringExtra("nameOfB");
                OwnerOfB=getintent.getStringExtra("OwnerOfB");
                Address=getintent.getStringExtra("Address");
                PhoneNumber=getintent.getStringExtra("PhoneNumber");
                nightdelivery=getintent.getIntExtra("nightDelivery",0);
                paytmAccepted=getintent.getIntExtra("paytmAccepted",0);
                Type=getintent.getStringExtra("type");

                vendordata= FirebaseDatabase.getInstance().getReference("vendors");
                deliveryInfo=FirebaseDatabase.getInstance().getReference("deliveryInfo");
                VendorInformation vendorInformation=new VendorInformation(nightdelivery,paytmAccepted,nameOFB,OwnerOfB,Address,PhoneNumber,DeliveryInfo,Type);

                vendordata.child(""+i).setValue(vendorInformation);
                if(DeliveryInfo.compareTo("YES,but Conditionally")==0)
                {
                   deliveryInfo.child(PhoneNumber).setValue(deliveryInformation);
                }
                i++;
                makeDialog();




            }
        });





//


    }
    void makeDialog(){
        AlertDialog.Builder d = new AlertDialog.Builder(this);
        d.setTitle("Registered");
        d.setMessage("Account has been created");


        d.setPositiveButton("Done", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                //do something with edt.getText().toString();
                Intent intent=new Intent(VendorInfo4.this,VendorLogin.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();

            }
        });
        d.show();

    }
    public void rbClick(View v){
        int radioButtonId=rg.getCheckedRadioButtonId();
        Rbtn=(RadioButton)findViewById(radioButtonId);

        String radioText=Rbtn.getText().toString();
        if(radioText.equals("YES,but Conditionally")){
            //Toast.makeText(getApplicationContext(),"THIS IS  "+Rbtn.getText(),Toast.LENGTH_SHORT).show();
            showChangeLangDialog();
            DeliveryInfo="YES,but Conditionally";


        }
        else
        if(radioText.equals("YES"))
        {
        DeliveryInfo="YES";


        }
        else
            if(radioText.equals("NO"))
            {
            DeliveryInfo="NO";


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

                minAmount = (EditText) dialogView.findViewById(R.id.edit1);
                distance = (EditText) dialogView.findViewById(R.id.edit2);
                chargesOtherwise = (EditText) dialogView.findViewById(R.id.edit3);


                if(minAmount.getText().equals("")||distance.getText().toString().equals("")||chargesOtherwise.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(),"Please enter all values",Toast.LENGTH_SHORT).show();

                }
                else{

                    deliveryInformation=new DeliveryInformation(minAmount.getText().toString(),distance.getText().toString(),chargesOtherwise.getText().toString());


                    b.dismiss();
                }
            }
        });

    }

}
//