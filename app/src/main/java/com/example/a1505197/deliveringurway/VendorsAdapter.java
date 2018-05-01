package com.example.a1505197.deliveringurway;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by 1505197 on 4/26/2018.
 */
public class VendorsAdapter extends RecyclerView.Adapter<VendorsAdapter.VendorViewHolder>
{
    Context context;
    ArrayList<VendorInformation> data;
    LayoutInflater inflater;

    public VendorsAdapter(Context context, ArrayList<VendorInformation> data)
    {
        this.context=context;
        this.data=data;
        inflater=LayoutInflater.from(context);

    }
    @Override
    public VendorViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view= inflater.inflate(R.layout.singlevendoritem,parent,false);
        VendorsAdapter.VendorViewHolder holder=new  VendorsAdapter.VendorViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(VendorViewHolder holder, int position)
    {


        holder.VendorName.setText(data.get(position).business_name);



    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class VendorViewHolder extends RecyclerView.ViewHolder
    {
        TextView VendorName;


        public VendorViewHolder(View itemView) {
            super(itemView);
            VendorName=itemView.findViewById(R.id.tvVendorName);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                int position=getAdapterPosition();
                    Intent intent=new Intent(context,UserSideVendorData.class);
                    intent.putExtra("VendorPhoneNumber",data.get(position).phone_number);
                    context.startActivity(intent);
                }
            });
        }
    }
}
////