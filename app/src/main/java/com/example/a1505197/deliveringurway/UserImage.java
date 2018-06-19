package com.example.a1505197.deliveringurway;

/**
 * Created by 1505197 on 6/20/2018.
 */

public class UserImage
{
    static String image_url;
    public UserImage()
    {

    }

    public UserImage(String image_url) {
        this.image_url = image_url;
    }

    public static String getImage_url() {
        return image_url;
    }

    public static void setImage_url(String image_url) {
        UserImage.image_url = image_url;
    }
}
