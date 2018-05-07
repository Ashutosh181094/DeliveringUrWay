package com.example.a1505197.deliveringurway;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
        View view= inflater.inflate(R.layout.commentcardview,parent,false);
        CommentAdapter.VendorViewHolder holder=new  CommentAdapter.VendorViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(VendorViewHolder holder, int position)
    {


        holder.tvComment.setText(data.get(position).comments);


    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class VendorViewHolder extends RecyclerView.ViewHolder
    {
       TextView tvComment;

        public VendorViewHolder(View itemView) {
            super(itemView);
            tvComment=itemView.findViewById(R.id.tvComment);

        }
    }
}
////