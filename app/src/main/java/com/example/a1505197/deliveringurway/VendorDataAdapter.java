package com.example.a1505197.deliveringurway;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by 1505197 on 3/26/2018.
 */

public class VendorDataAdapter extends RecyclerView.Adapter<VendorDataAdapter.VendorViewHolder>
{
    Context context;
    ArrayList<ProductDescription> data;
    LayoutInflater inflater;

    public VendorDataAdapter(Context context, ArrayList<ProductDescription> data)
    {
        this.context=context;
        this.data=data;
        inflater=LayoutInflater.from(context);

    }
    @Override
    public VendorViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view= inflater.inflate(R.layout.vendor_items_card,parent,false);
        VendorDataAdapter.VendorViewHolder holder=new  VendorDataAdapter.VendorViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(VendorViewHolder holder, int position)
    {
        holder.productName.setText(data.get(position).productName);
        holder.price.setText(data.get(position).cost);
        Picasso.with(context)
                .load(data.get(position).imageUrl)
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
        TextView productName;
        TextView price;
        ImageView imageView;

        public VendorViewHolder(View itemView) {
            super(itemView);
            productName=itemView.findViewById(R.id.CardViewProductName);
            price=itemView.findViewById(R.id.PriceEntered);
            imageView=itemView.findViewById(R.id.CardViewImageView);

        }
    }
}
