package com.example.a1505197.deliveringurway;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class UserSideVendorDataClothAdapter extends RecyclerView.Adapter<UserSideVendorDataClothAdapter.VendorViewHolder>
{
    Context context;
    ArrayList<ClothProductDescription> data;
    LayoutInflater inflater;
    String phoneNumber;

    public UserSideVendorDataClothAdapter(Context context, ArrayList<ClothProductDescription> data, String phnoneNumber)
    {
        this.context=context;
        this.data=data;
        this.phoneNumber=phnoneNumber;
        inflater=LayoutInflater.from(context);

    }
    @Override
    public VendorViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view= inflater.inflate(R.layout.product_card_view,parent,false);
        UserSideVendorDataClothAdapter.VendorViewHolder holder=new  UserSideVendorDataClothAdapter.VendorViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(VendorViewHolder holder, int position)
    {
        String p_name=data.get(position).product_name;
        String pname=getSafeSubstring(p_name,10)+"...";

        holder.productname.setText(pname);
        holder.price.setText(data.get(position).cost);
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
        TextView price;
        ImageView imageView;

        public VendorViewHolder(View itemView) {
            super(itemView);
            productname=itemView.findViewById(R.id.product_card_text_description);
            price=itemView.findViewById(R.id.PriceEntered);
            imageView=itemView.findViewById(R.id.product_card_Image);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
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
    public String getSafeSubstring(String s, int maxLength){
        if(!TextUtils.isEmpty(s)){
            if(s.length() >= maxLength){
                return s.substring(0, maxLength);
            }
        }
        return s;
    }
}
///////////