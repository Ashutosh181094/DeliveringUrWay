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

import java.util.List;


public class VendorDataAdapter extends RecyclerView.Adapter<VendorDataAdapter.VendorViewHolder>
{
    Context context;
    List<ProductDescription> data;
    LayoutInflater inflater;

    public VendorDataAdapter(Context context, List<ProductDescription> data)
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


        holder.productname.setText(data.get(position).productName);
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
        TextView productname;
        TextView price;
        ImageView imageView;

        public VendorViewHolder(View itemView) {
            super(itemView);
            productname=itemView.findViewById(R.id.CardViewProductName);
            price=itemView.findViewById(R.id.PriceEntered);
            imageView=itemView.findViewById(R.id.CardViewImageView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position=getAdapterPosition();
                    Intent intent=new Intent(context,VendorProductDescription.class);
                    intent.putExtra("name",data.get(position).productName);
                    intent.putExtra("cost",data.get(position).cost);
                    intent.putExtra("description",data.get(position).description);
                    intent.putExtra("imageurl",data.get(position).imageUrl);
                    context.startActivity(intent);
                }
            });
        }
    }
}
