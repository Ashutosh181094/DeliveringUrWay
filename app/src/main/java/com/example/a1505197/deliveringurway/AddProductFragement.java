package com.example.a1505197.deliveringurway;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by 1505197 on 3/12/2018.
 */

public class AddProductFragement extends Fragment implements ChangePhotoDialog.OnPhotoRecievedListener {
   ImageView image,vendorImage;
    private String mSelectedImagePath;
    DatabaseReference productinfo;
    EditText name,cost,description;
    String sname,scost,sdescription;
    Button buttonUpload;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.activity_add_product,container,false);
        image=view.findViewById(R.id.ivCamera);
        vendorImage=view.findViewById(R.id.vendorImage);
        name=view.findViewById(R.id.etName);
        cost=view.findViewById(R.id.etCost);
        description=view.findViewById(R.id.etdescription);


        buttonUpload=view.findViewById(R.id.btnUpload);
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(int i = 0; i< Init.PERMISSIONS.length; i++)
                {
                    String[] permission={Init.PERMISSIONS[i]};
                    if(((VendorData)getActivity()).checkPermissions(permission))
                    {
                        if(i==Init.PERMISSIONS.length-1)
                        {
                            ChangePhotoDialog dialog=new ChangePhotoDialog();
                            dialog.show(getFragmentManager(),getString(R.string.change_photo_dialog));
                            dialog.setTargetFragment(AddProductFragement.this,0);
                        }
                    }
                    else
                    {
                        ((VendorData)getActivity()).verifyPermissions(permission);
                    }
                }
            }
        });
        buttonUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
                productinfo= FirebaseDatabase.getInstance().getReference("productinfo");

                sname=name.getText().toString();
                scost=cost.getText().toString();
               sdescription=description.getText().toString();
                ProductDescription pdescription=new ProductDescription(sname,scost,sdescription);
                productinfo.child(user.getUid()).child(sname).setValue(pdescription);
            }
        });
        return view;
    }

    @Override
    public void getBitmapImage(Bitmap bitmap) {
        if(bitmap!=null)
        {
            ((VendorData)getActivity()).compressBitmap(bitmap,70);
            vendorImage.setImageBitmap(bitmap);
        }
    }

    @Override
    public void getImagePath(String imagePath) {
        if(!imagePath.equals(""))
        {
            imagePath=imagePath.replace(":/","://");
            mSelectedImagePath=imagePath;
            UniversalImageLoader.setImage(imagePath,vendorImage,null,"");


        }
    }
}
