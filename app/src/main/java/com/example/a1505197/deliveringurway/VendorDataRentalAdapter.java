package com.example.a1505197.deliveringurway;

import android.app.Dialog;
import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
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


public class VendorDataRentalAdapter extends RecyclerView.Adapter<VendorDataRentalAdapter.VendorViewHolder>
{
    Context context;
    List<RentalProductDescription> data;
    LayoutInflater inflater;
    DatabaseReference VendorTypeInfo;
    DatabaseReference VendorProductedit;
    ArrayList<RetrieveVendorType> vendortype;
    FirebaseUser user;

    public VendorDataRentalAdapter(Context context, List<RentalProductDescription> data)
    {
        this.context=context;
        this.data=data;
        inflater=LayoutInflater.from(context);
        Toast.makeText(context, ""+data.size(), Toast.LENGTH_SHORT).show();


    }
    @Override
    public VendorViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view= inflater.inflate(R.layout.rental_product_card_view,parent,false);
        VendorDataRentalAdapter.VendorViewHolder holder=new  VendorDataRentalAdapter.VendorViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final VendorViewHolder holder, final int position)
    {


        holder.productname.setText(data.get(position).product_name);
        holder.costperday.setText(data.get(position).cost_per_day);
        holder.costperhour.setText(data.get(position).cost_per_hour);
        Picasso.with(context)
                .load(data.get(position).image_url)
                .fit()
                .centerCrop()
                .into(holder.imageView);
        holder.popupimageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "clicked", Toast.LENGTH_SHORT).show();
                PopupMenu popupMenu=new PopupMenu(context,holder.popupimageView);
                popupMenu.inflate(R.menu.edit_rental_menu);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId())
                        {
                            case R.id.edit_rental_menu_edit_photo:
                                Toast.makeText(context, "edit photo", Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.edit_rental_menu_edit_cost_per_hour:
                                final Dialog dialog=new Dialog(context);
                                dialog.setContentView(R.layout.dialog_edit_rental_cost_per_hour);
                                dialog.show();
                                Button upload=dialog.findViewById(R.id.cost_per_hour_update);
                                upload.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        EditText costperhour=dialog.findViewById(R.id.et_cost_per_hour);
                                        String scostperhour=costperhour.getText().toString();

                                        VendorProductedit=FirebaseDatabase.getInstance().getReference("productinfo").child("Rental")
                                                .child(""+FirebaseAuth.getInstance().getCurrentUser().getPhoneNumber())
                                                .child(""+data.get(position).product_name);
                                        VendorProductedit.child("cost_per_hour").setValue((Object)scostperhour);
                                        dialog.dismiss();
                                    }
                                });




                                break;
                                case R.id.edit_rental_menu_edit_cost_per_day:
                                    final Dialog dialog2=new Dialog(context);
                                    dialog2.setContentView(R.layout.dialog_edit_rental_cost_per_day);
                                    dialog2.show();
                                    Button uploadcostperday=dialog2.findViewById(R.id.cost_per_day_update);
                                    uploadcostperday.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            EditText costperday=dialog2.findViewById(R.id.et_cost_per_day);
                                            String scostperday=costperday.getText().toString();

                                            VendorProductedit=FirebaseDatabase.getInstance().getReference("productinfo").child("Rental")
                                                    .child(""+FirebaseAuth.getInstance().getCurrentUser().getPhoneNumber())
                                                    .child(""+data.get(position).product_name);
                                            VendorProductedit.child("cost_per_day").setValue((Object)scostperday);
                                            dialog2.dismiss();
                                        }
                                    });

                                    break;

                        }
                        return false;
                    }
                });
                popupMenu.show();
            }
        });

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
        ImageView imageView,popupimageView;

        public VendorViewHolder(View itemView) {
            super(itemView);
            productname=itemView.findViewById(R.id.product_card_text_description);
            costperhour=itemView.findViewById(R.id.PriceEnteredCostPerHour);
            costperday=itemView.findViewById(R.id.PriceEnteredCostPerDay);
            imageView=itemView.findViewById(R.id.product_card_Image);
            popupimageView=itemView.findViewById(R.id.more_optionsRental);
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
                            Toast.makeText(context, ""+data.size(), Toast.LENGTH_SHORT).show();
                            FirebaseUser user=FirebaseAuth.getInstance().getCurrentUser();
                            DatabaseReference deletedata=FirebaseDatabase.getInstance().getReference("productinfo").child("Rental").child(""+user.getPhoneNumber()).child(""+data.get(position).product_name);
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