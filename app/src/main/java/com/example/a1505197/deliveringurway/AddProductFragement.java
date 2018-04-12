package com.example.a1505197.deliveringurway;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
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
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;

/**
 * Created by 1505197 on 3/12/2018.
 */

public class AddProductFragement extends Fragment implements ChangePhotoDialog.OnPhotoRecievedListener {
   ImageView vendorImage;
   ImageView image;
    private String mSelectedImagePath;
    DatabaseReference productinfo;
    EditText name,cost,description;
    String sname,scost,sdescription;
    Button buttonUpload;
    StorageReference storePhoto;
    ProgressDialog progressDialog;
    FirebaseUser user;
   static int i=0;

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

                sname=name.getText().toString();
                scost=cost.getText().toString();
                sdescription=description.getText().toString();

                 user= FirebaseAuth.getInstance().getCurrentUser();
                productinfo= FirebaseDatabase.getInstance().getReference("productinfo");
                storePhoto= FirebaseStorage.getInstance().getReference(user.getPhoneNumber()+"/"+sname);
                // Get the data from an ImageView as bytes
                vendorImage.setDrawingCacheEnabled(true);
                vendorImage.buildDrawingCache();
                Bitmap bitmap = vendorImage.getDrawingCache();
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                byte[] data = baos.toByteArray();
                createProgressDialog();

                UploadTask uploadTask = storePhoto.putBytes(data);
                uploadTask.addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        // Handle unsuccessful uploads
                        dismissDialog();
                    }
                }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        // taskSnapshot.getMetadata() contains file metadata such as size, content-type, and download URL.
                        Uri downloadUrl = taskSnapshot.getDownloadUrl();
                        Toast.makeText(getContext(), "Photo Uploaded", Toast.LENGTH_SHORT).show();
                        dismissDialog();
                        ProductDescription pdescription=new ProductDescription(sname,scost,sdescription,taskSnapshot.getDownloadUrl().toString());
                        productinfo.child(user.getPhoneNumber()).child(""+i++).setValue(pdescription);
                        Intent intent=new Intent(getContext(),VendorData.class);
                        startActivity(intent);


                    }
                });



            }
        });
        return view;
    }

    @Override
    public void getBitmapImage(Bitmap bitmap) {
        if(bitmap!=null)
        {

            ((VendorData)getActivity()).compressBitmap(bitmap,100);
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

    @Override
    public Uri getImageUri(Uri Imageuri) {

        return Imageuri;
    }
    public void createProgressDialog()
    {
        progressDialog=new ProgressDialog(getContext());
        progressDialog.setMessage("Uploaing Wait");

        progressDialog.show();
    }
    public void dismissDialog()
    {
       progressDialog.dismiss();
    }

    @Override
    public void onStop() {
        super.onStop();
        SharedPreferences sp = getActivity().getSharedPreferences("IValue", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt("i", i);
        editor.commit();

    }

    @Override
    public void onStart() {
        super.onStart();
        SharedPreferences sp =getActivity().getSharedPreferences("IValue", Activity.MODE_PRIVATE);
        int Value = sp.getInt("your_int_key", -1);
    }
}
