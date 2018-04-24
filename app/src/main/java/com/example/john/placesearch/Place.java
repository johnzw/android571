package com.example.john.placesearch;

public class Place {
    public String icon;
    public String name;
    public String address;
    public String id;
    public boolean isLiked;

    public Place(String id, String name, String address, String icon){
        this.icon = icon;
        this.name = name;
        this.address = address;
        this.id = id;
        this.isLiked = false;
    }
}
