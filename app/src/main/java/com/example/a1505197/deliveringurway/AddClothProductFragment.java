package com.example.a1505197.deliveringurway;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
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
 * Created by 1505197 on 6/8/2018.
 */

public class AddClothProductFragment extends Fragment implements ChangePhotoDialog.OnPhotoRecievedListener
{
    ImageView vendorImage;
    ImageView image;
    private String mSelectedImagePath;
    DatabaseReference productinfo;
    EditText name,cost,description,sizeAvailable;
    String sname,scost,sdescription,Size;
    Button buttonUpload;
    StorageReference storePhoto;
    ProgressDialog progressDialog;
    FirebaseUser user;
    String categories[]={"S","M","L","XL"};
    Spinner size;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.addclothfragment,container,false);
        image=view.findViewById(R.id.ivCamera);
        vendorImage=view.findViewById(R.id.vendorImage);
        name=view.findViewById(R.id.etName);
        cost=view.findViewById(R.id.etCost);
        description=view.findViewById(R.id.etdescription);
        size=view.findViewById(R.id.spinnerSizeChart);
        ArrayAdapter dataAdapter=new ArrayAdapter(getActivity(),R.layout.support_simple_spinner_dropdown_item,categories);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        size.setAdapter(dataAdapter);
       size.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           @Override
           public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               if(position==0)
               {
                  Size ="Size Available->S";
               }
               else if (position==1)
               {
                   Size="Size Available->M";
               }
               else if(position==2) {
                   Size = "Size Available->L";
               }
               else
               {
                   Size="Size Available->Xl";
               }
           }

           @Override
           public void onNothingSelected(AdapterView<?> parent) {

           }
       });



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
                            dialog.setTargetFragment(AddClothProductFragment.this,0);
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
                if(sname.equals("")||scost.equals("")||sdescription.equals(""))
                {
                    Toast.makeText(getContext(), "Fill all the fields", Toast.LENGTH_SHORT).show();
                }
               else
                {
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
                            ClothProductDescription pdescription=new ClothProductDescription(sname,scost,Size,sdescription,taskSnapshot.getDownloadUrl().toString());
                            productinfo.child("Clothing").child(user.getPhoneNumber()).child(sname).setValue(pdescription);
                            getFragmentManager().popBackStack();


                        }
                    });
                }




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

    }

    @Override
    public void onStart() {
        super.onStart();

    }
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setCancelable(false);
        builder.setMessage("Are you sure you want to Cancel adding product?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                //if user pressed "yes", then he is allowed to exit from application
                Intent intent= new Intent(AddClothProductFragment.this.getActivity(),VendorData.class);
                startActivity(intent);

                //finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //if user select "No", just cancel this dialog and continue with app
                dialog.cancel();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }
}
////