package com.example.john.placesearch;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Review {
    public String authorName;
    public String authorURL;
    public String photoURL;
    public String content;
    public Date time;
    public int rating;
    public static Format timeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public Review(String authorName, String authorURL, String photoURL, int rating, String content, long time) {
        this.authorName = authorName;
        this.authorURL = authorURL;
        this.photoURL = photoURL;
        this.content = content;
        this.rating = rating;
        this.time = new Date(time);
    }
}
