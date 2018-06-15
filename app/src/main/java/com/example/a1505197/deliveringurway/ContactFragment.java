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
    TextView Address,paymentOption,lateNightDeliveryAnswer,freedeliverydetails,minorderprice,minorderdistance,chargesotherwise,simpleminorderprice,simpleminorderdistance,simplechargesotherwise;
    Button ContactPhone;
    ArrayList<VendorInformation> vendorInformations;
    ArrayList<DeliveryInformation> deliveryInformations;
    DatabaseReference Vendorcontactinfo,DeliveryInfo;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_contact,container,false);
        vendorInformations=new ArrayList<>();
        deliveryInformations=new ArrayList<>();
        Intent intent=getActivity().getIntent();
        final String phno=intent.getStringExtra("VendorPhoneNumber");
        freedeliverydetails=view.findViewById(R.id.contact_details_delivery_free_detail_textAnswer);
        minorderprice=view.findViewById(R.id.contact_details_delivery_Conditonally_detail_min_price_textAnswer);
        minorderdistance=view.findViewById(R.id.contact_details_delivery_Conditonally_detail_min_dist_textAnswer);
        chargesotherwise=view.findViewById(R.id.contact_details_delivery_Conditonally_detail_charges_otherwise_textAnswer);
        simpleminorderprice=view.findViewById(R.id.contact_details_delivery_conditional_detail_text);
        simpleminorderdistance=view.findViewById(R.id.contact_details_delivery_conditional_detail_min_dist_text);
        simplechargesotherwise=view.findViewById(R.id.contact_details_delivery_conditional_detail_charges_otherwise_text);
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
                    if(vendorInformations.get(0).delivery_info.equals("YES"))
                    {
                        freedeliverydetails.setText("YES");
                        minorderdistance.setVisibility(View.GONE);
                        minorderprice.setVisibility(View.GONE);
                        chargesotherwise.setVisibility(View.GONE);
                        simplechargesotherwise.setVisibility(View.GONE);
                        simpleminorderdistance.setVisibility(View.GONE);
                        simpleminorderprice.setVisibility(View.GONE);
                    }
                    else
                    if(vendorInformations.get(0).delivery_info.equals("NO"))
                    {
                        freedeliverydetails.setText("NO");
                        minorderdistance.setVisibility(View.GONE);
                        minorderprice.setVisibility(View.GONE);
                        chargesotherwise.setVisibility(View.GONE);
                        simplechargesotherwise.setVisibility(View.GONE);
                        simpleminorderdistance.setVisibility(View.GONE);
                        simpleminorderprice.setVisibility(View.GONE);
                    }
                    else
                    {
                        freedeliverydetails.setText("YES,but Conditionally");
                        DeliveryInfo=FirebaseDatabase.getInstance().getReference("deliveryInfo").child(""+phno);
                        DeliveryInfo.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot)
                            {
                                String value[]=new String[3];
                                int i=0;

                                if (dataSnapshot.exists())
                                {
                                    for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren())
                                    {

                                        value[i]=(String)dataSnapshot1.getValue();
                                        i++;

                                    }

                                }

                                minorderdistance.setText(value[1]);
                                minorderprice.setText(value[2]);
                                chargesotherwise.setText(value[0]);
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });

                    }


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
///////////