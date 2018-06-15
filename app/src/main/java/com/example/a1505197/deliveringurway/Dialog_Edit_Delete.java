package com.example.a1505197.deliveringurway;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;

/**
 * Created by 1505197 on 6/15/2018.
 */

public class Dialog_Edit_Delete extends AppCompatDialogFragment
{
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        LayoutInflater inflater=getActivity().getLayoutInflater();
        View view=inflater.inflate(R.layout.dialog_edit_delete,null);
        builder.setView(view);
                return builder.create();
    }
}
