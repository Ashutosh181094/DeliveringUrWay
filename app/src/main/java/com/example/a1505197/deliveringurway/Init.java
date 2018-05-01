package com.example.a1505197.deliveringurway;

import android.Manifest;

/**
 * Created by 1505197 on 9/22/2017.
 */

public class Init {
    public Init() {
    }
    public static final String[] PERMISSIONS={
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA};
    public static final String[] PHONE_PERMISSION={Manifest.permission.CALL_PHONE};
    public static final String[] INTERNET_PERMISSION={Manifest.permission.INTERNET};
    public static final int CAMERA_REQUEST_CODE=5;
    public static final int PICKFILE_REQUEST_CODE=8;
}
//