package com.example.john.placesearch;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class FavouritePlaces {

    private static HashSet<String> placeMap = new HashSet<>();
    private static LinkedList<Place> placeList = new LinkedList<>();

    public static boolean isFavourite(String placeId){
        return placeMap.contains(placeId);
    }

    public static void addToFavouritePlaces(Place place){
        placeMap.add(place.id);
        placeList.addFirst(place);
    }

    public static void removeFromFavouritePlaces(String id){
        placeMap.remove(id);
        for(int i=0; i<placeList.size(); i++){
            if(placeList.get(i).id == id){
                placeList.remove(i);
                return;
            }
        }
    }

    public static List<Place> getPlaceList(){
        return placeList;
    }

}
