package com.example.john.placesearch;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.location.places.GeoDataClient;
import com.google.android.gms.location.places.PlacePhotoMetadataBuffer;
import com.google.android.gms.location.places.PlacePhotoMetadataResponse;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ReviewFragment extends Fragment {
    private AdapterReview mAdapter;


    public ReviewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_review, container, false);
        populatePhotos(view);
        return view;
    }

    private void populatePhotos(final View view) {
        JSONObject jsonObj = ((PlaceDetailActivity)getActivity()).getContextJson();
        //get jsonObj
        List<Review> reviewList = new ArrayList<>();
        try {
            JSONArray reviewsJson = jsonObj.getJSONArray("reviews");
            for(int i=0;i<reviewsJson.length();i++){
                JSONObject json_data = reviewsJson.getJSONObject(i);

                Review review = new Review(
                        json_data.getString("author_name"),
                        json_data.getString("author_url"),
                        json_data.getString("profile_photo_url"),
                        json_data.getInt("rating"),
                        json_data.getString("text"),
                        json_data.getLong("time"));

                reviewList.add(review);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        RecyclerView recyclerView = (RecyclerView)view.findViewById(R.id.recyclerviewreviews);
        RecyclerViewClickListener listener = new RecyclerViewClickListener(){
            @Override
            public void onClick(View view, int position) {
                Review r = mAdapter.getReview(position);
                Toast.makeText(getContext(), r.authorURL, Toast.LENGTH_SHORT).show();
            }
        };
        mAdapter = new AdapterReview(getContext(), reviewList, listener);

        recyclerView.setAdapter(mAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

    }

}
