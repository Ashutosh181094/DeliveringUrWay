package com.example.a1505197.deliveringurway;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


public class UserSideVendorDataRentalAdapter extends RecyclerView.Adapter<UserSideVendorDataRentalAdapter.VendorViewHolder>
{
    Context context;
    List<RentalProductDescription> data;
    LayoutInflater inflater;

    ArrayList<RetrieveVendorType> vendortype;
    String phoneNumber;


    public UserSideVendorDataRentalAdapter(Context context, List<RentalProductDescription> data,String phnoneNumber)
    {
        this.context=context;
        this.data=data;
        this.phoneNumber=phnoneNumber;
        inflater=LayoutInflater.from(context);

    }
    @Override
    public VendorViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view= inflater.inflate(R.layout.rental_product_card_view,parent,false);
        UserSideVendorDataRentalAdapter.VendorViewHolder holder=new  UserSideVendorDataRentalAdapter.VendorViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(VendorViewHolder holder, int position)
    {


        holder.productname.setText(data.get(position).product_name);
        holder.costperday.setText(data.get(position).cost_per_day);
        holder.costperhour.setText(data.get(position).cost_per_hour);
        Picasso.with(context)
                .load(data.get(position).image_url)
                .fit()
                .centerCrop()
                .into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class VendorViewHolder extends RecyclerView.ViewHolder
    {
        TextView productname;
        TextView costperhour;
        TextView costperday;
        ImageView imageView;

        public VendorViewHolder(View itemView) {
            super(itemView);
            productname=itemView.findViewById(R.id.product_card_text_description);
            costperhour=itemView.findViewById(R.id.PriceEnteredCostPerHour);
            costperday=itemView.findViewById(R.id.PriceEnteredCostPerDay);
            imageView=itemView.findViewById(R.id.product_card_Image);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v)
                {
                    int pos;
                    pos=getAdapterPosition();
                    Intent intent=new Intent(context,AboutProduct.class);
                    intent.putExtra("productName",data.get(pos).product_name);
                    intent.putExtra("phoneNumber",phoneNumber);
                    context.startActivity(intent);


                }
            });

        }
    }
}
////////////