package com.example.a1505197.deliveringurway;

/**
 * Created by 1505197 on 5/8/2018.
 */

public class UserInfo {
    String name;
    String image_url;
    String email;

    public UserInfo() {
    }

    public UserInfo(String name, String image_url, String email) {
        this.name = name;
        this.image_url = image_url;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
