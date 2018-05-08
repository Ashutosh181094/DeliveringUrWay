package com.example.a1505197.deliveringurway;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.File;

/**
 * Created by 1505197 on 9/23/2017.
 */

public class ChangePhotoDialog extends DialogFragment {

    public interface OnPhotoRecievedListener
    {
        public void getBitmapImage(Bitmap bitmap);
        public void getImagePath(String imagePath);
    }
    OnPhotoRecievedListener mOnPhotoRecieved;
    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.dialog_changephoto,container,false);
        TextView takePhoto=(TextView)view.findViewById(R.id.dialogTakePhoto);
        takePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cameraIntent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent,Init.CAMERA_REQUEST_CODE);
            }
        });
        TextView selectPhoto=(TextView)view.findViewById(R.id.dialogChoosePhoto);
        selectPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
         Intent intent=new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent,Init.PICKFILE_REQUEST_CODE);
            }
        });
        TextView cancelDialog=(TextView)view.findViewById(R.id.dialogCancel);
        cancelDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
           getDialog().dismiss();
            }
        });
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try{
      mOnPhotoRecieved=(OnPhotoRecievedListener)getTargetFragment();
        }
        catch(ClassCastException e)
        {

        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==Init.CAMERA_REQUEST_CODE&&resultCode== Activity.RESULT_OK)
        {
            Bitmap bitmap=(Bitmap)data.getExtras().get("data");
           mOnPhotoRecieved.getBitmapImage(bitmap);
            getDialog().dismiss();
        }
        if (requestCode==Init.PICKFILE_REQUEST_CODE && resultCode == Activity.RESULT_OK)
        {
            Uri selectedImageUri=data.getData();
            File file=new File(selectedImageUri.toString());
            mOnPhotoRecieved.getImagePath(file.getPath());
            getDialog().dismiss();

        }
    }
}
//