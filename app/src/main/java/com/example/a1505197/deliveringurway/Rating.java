package com.example.a1505197.deliveringurway;

public class Rating  {
    String user,comments;
  int rating;
    public Rating() {
    }

    public Rating(String user, String comments, int rating) {
        this.user = user;
        this.comments = comments;
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

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
