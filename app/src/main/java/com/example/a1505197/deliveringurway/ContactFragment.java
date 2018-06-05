package com.example.a1505197.deliveringurway;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class ContactFragment extends Fragment
{
    TextView Address,paymentOption,lateNightDeliveryAnswer;
    Button ContactPhone;
    ArrayList<VendorInformation> vendorInformations;
    DatabaseReference Vendorcontactinfo;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_contact,container,false);
        vendorInformations=new ArrayList<>();

        Intent intent=getActivity().getIntent();
        final String phno=intent.getStringExtra("VendorPhoneNumber");
        Address=view.findViewById(R.id.contact_details_address_text);
        paymentOption=view.findViewById(R.id.contact_details_payment_title_textAnswer);
        lateNightDeliveryAnswer=view.findViewById(R.id.contact_details_late_night_delivery_answer);
        ContactPhone=view.findViewById(R.id.contact_details_contact_text);
        VendorType vendorType=new VendorType();
        String Type=vendorType.getType();
        Vendorcontactinfo = FirebaseDatabase.getInstance().getReference("vendors").child(""+Type);
        Vendorcontactinfo.addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot)
            {
                if (dataSnapshot.exists())
                {

                    vendorInformations.clear();

                    for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren())
                    {
                        VendorInformation vendorInformation = dataSnapshot1.getValue(VendorInformation.class);
                        if(vendorInformation.phone_number.compareTo(phno)==0)
                        {
                            vendorInformations.add(vendorInformation);
                        }
                    }
                    Address.setText(vendorInformations.get(0).address);
                    if(vendorInformations.get(0).pay_tm_accepted==1)
                    {
                        paymentOption.setText("YES");
                    }
                    else
                    {
                        paymentOption.setText("NO");
                    }
                    if(vendorInformations.get(0).night_delivery==1){
                        lateNightDeliveryAnswer.setText("YES");

                    }
                    else {
                        lateNightDeliveryAnswer.setText("NO");
                    }
                   ContactPhone.setOnClickListener(new View.OnClickListener() {
                       @Override
                       public void onClick(View v) {
                           Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + vendorInformations.get(0).phone_number));
                           startActivity(intent);

                       }
                   });


                }

            }


            @Override
            public void onCancelled(DatabaseError databaseError)
            {

            }
        });
        return view;

    }
}
////