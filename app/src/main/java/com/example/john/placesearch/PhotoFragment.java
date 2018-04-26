package com.example.john.placesearch;


import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.location.places.GeoDataClient;
import com.google.android.gms.location.places.PlacePhotoMetadata;
import com.google.android.gms.location.places.PlacePhotoMetadataBuffer;
import com.google.android.gms.location.places.PlacePhotoMetadataResponse;
import com.google.android.gms.location.places.PlacePhotoResponse;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import org.json.JSONException;
import org.json.JSONObject;


/**
 * A simple {@link Fragment} subclass.
 */
public class PhotoFragment extends Fragment {


    public PhotoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_photo, container, false);
        populatePhotos(v);
        return v;
    }

    // Request photos and metadata for the specified place.
    private void populatePhotos(final View view) {
        JSONObject jsonObj = ((PlaceDetailActivity)getActivity()).getContextJson();
        String placeId = "b85217d74722f6fec94a4135f209e13092d81a5e";
        try {
            placeId = jsonObj.getString("place_id");
        } catch (JSONException e) {
            e.printStackTrace();
        }

//        placeId = "ChIJa147K9HX3IAR-lwiGIQv9i4";
        final GeoDataClient mGeoDataClient = ((PlaceDetailActivity)getActivity()).getmGeoDataClient();
        final Task<PlacePhotoMetadataResponse> photoMetadataResponse = mGeoDataClient.getPlacePhotos(placeId);

        photoMetadataResponse.addOnCompleteListener(new OnCompleteListener<PlacePhotoMetadataResponse>() {
            @Override
            public void onComplete(@NonNull Task<PlacePhotoMetadataResponse> task) {
                // Get the list of photos.
                PlacePhotoMetadataResponse photos = task.getResult();
                // Get the PlacePhotoMetadataBuffer (metadata for all of the photos).
                PlacePhotoMetadataBuffer photoMetadataBuffer = photos.getPhotoMetadata();
                // Get the first photo in the list.
                int size = photoMetadataBuffer.getCount();

                RecyclerView recyclerView = (RecyclerView)view.findViewById(R.id.recyclerphoto);

                AdapterPhoto mAdapter = new AdapterPhoto(getContext(), photoMetadataBuffer);

                recyclerView.setAdapter(mAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            }
        });
    }

}
