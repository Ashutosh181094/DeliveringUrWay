package com.example.a1505197.deliveringurway;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;


public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.VendorViewHolder>
{
    Context context;
    List<Rating> data;
    LayoutInflater inflater;

    public CommentAdapter(Context context, List<Rating> data)
    {
        this.context=context;
        this.data=data;
        inflater=LayoutInflater.from(context);

    }
    @Override
    public VendorViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view= inflater.inflate(R.layout.single_row,parent,false);
        CommentAdapter.VendorViewHolder holder=new  CommentAdapter.VendorViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(VendorViewHolder holder, int position)
    {


        holder.tvComment.setText(data.get(position).comments);
        holder.tvComment.setTextSize(15);
        Picasso.with(context)
                .load(data.get(position).image_url)
                .fit()
                .centerCrop()
                .into(holder.userImage);


    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class VendorViewHolder extends RecyclerView.ViewHolder
    {
       TextView tvComment;
       ImageView userImage;

        public VendorViewHolder(View itemView) {
            super(itemView);
            tvComment=itemView.findViewById(R.id.textView);
            userImage=itemView.findViewById(R.id.imageView);

        }
    }
}
////////////