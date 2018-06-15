package com.example.a1505197.deliveringurway;

public class Rating  {
    String user,comments,image_url;
       int rating;
    public Rating() {
    }

    public Rating(String user, String comments, String image_url, int rating) {
        this.user = user;
        this.comments = comments;
        this.image_url = image_url;
        this.rating = rating;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
//////////////