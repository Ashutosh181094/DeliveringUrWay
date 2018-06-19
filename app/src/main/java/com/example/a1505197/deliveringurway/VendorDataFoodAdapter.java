package com.example.a1505197.deliveringurway;

import android.app.Dialog;
import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


public class VendorDataFoodAdapter extends RecyclerView.Adapter<VendorDataFoodAdapter.VendorViewHolder>
{
    Context context;
    List<FoodProductDescription> data;
    LayoutInflater inflater;
    DatabaseReference VendorTypeInfo;
    ArrayList<RetrieveVendorType> vendortype;
    String Type;


    public VendorDataFoodAdapter(Context context, List<FoodProductDescription> data)
    {
        this.context=context;
        this.data=data;
        inflater=LayoutInflater.from(context);

    }
    @Override
    public VendorViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view= inflater.inflate(R.layout.product_card_view,parent,false);
        VendorDataFoodAdapter.VendorViewHolder holder=new  VendorDataFoodAdapter.VendorViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(VendorViewHolder holder, int position)
    {


        holder.productname.setText(data.get(position).product_name);
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
            price=itemView.findViewById(R.id.product_card_view_PriceRupeeSymbol);
            imageView=itemView.findViewById(R.id.product_card_Image);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final int position=getAdapterPosition();
                    GetEditProductNameInFragment getEditProductNameInFragment=new GetEditProductNameInFragment();
                    getEditProductNameInFragment.setName(data.get(position).product_name);
                    final Dialog dialog=new Dialog(context);
                    dialog.setContentView(R.layout.dialog_edit_delete);
                    dialog.show();
                    Button button_edit=dialog.findViewById(R.id.btn_edit);
                    Button button_delete=dialog.findViewById(R.id.btn_delete);
                    vendortype=new ArrayList<>();

                    button_edit.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v)
                        {
                            FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();

                            VendorTypeInfo= FirebaseDatabase.getInstance().getReference("VendorsType").child(""+user.getPhoneNumber());
                            VendorTypeInfo.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    if (dataSnapshot.exists())
                                    {
                                        vendortype.clear();
                                        for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren())
                                        {
                                            RetrieveVendorType retrieveVendorType= dataSnapshot1.getValue(RetrieveVendorType.class);
                                            vendortype.add(retrieveVendorType);

                                        }
                                        Toast.makeText(context,""+vendortype.get(0).type,Toast.LENGTH_LONG).show();
                                        Type=vendortype.get(0).type;
                                    }
                                    if (vendortype.get(0).type.equals("Food"))
                                    {
                                        EditFoodProductFragment fragment=new EditFoodProductFragment();
                                        FragmentTransaction transaction=((FragmentActivity)context).getSupportFragmentManager().beginTransaction();
                                        transaction.replace(android.R.id.content,fragment);
                                        transaction.addToBackStack(null);
                                        transaction.commit();
                                        dialog.dismiss();


                                    }
                                    else
                                    if(vendortype.get(0).type.equals("Clothing"))
                                    {
                                        EditClothProductFragment fragment=new EditClothProductFragment();
                                        FragmentTransaction transaction=((FragmentActivity)context).getSupportFragmentManager().beginTransaction();
                                        transaction.replace(android.R.id.content,fragment);
                                        transaction.addToBackStack(null);
                                        transaction.commit();
                                        dialog.dismiss();

                                    }
                                    else
                                    {
                                        EditRentalProductFragment fragment=new EditRentalProductFragment();
                                        FragmentTransaction transaction=((FragmentActivity)context).getSupportFragmentManager().beginTransaction();
                                        transaction.replace(android.R.id.content,fragment);
                                        transaction.addToBackStack(null);
                                        transaction.commit();
                                        dialog.dismiss();
                                    }

                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {

                                }
                            });


                        }
                    });
                    button_delete.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            FirebaseUser user=FirebaseAuth.getInstance().getCurrentUser();
                            DatabaseReference deletedata=FirebaseDatabase.getInstance().getReference("productinfo").child(""+Type).child(""+data.get(position).product_name);
                            DatabaseReference deleteproductCommentsandRating=FirebaseDatabase.getInstance().getReference(""+user.getPhoneNumber()).child(""+data.get(position).product_name);
                            deletedata.removeValue();
                            deleteproductCommentsandRating.removeValue();
                            dialog.dismiss();
                        }
                    });


                }
            });

        }
    }
}
////////////