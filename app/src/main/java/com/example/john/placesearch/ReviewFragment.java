package com.example.john.placesearch;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ReviewFragment extends Fragment {
    private AdapterReview mAdapter;
    private RecyclerView recyclerView;


    public ReviewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_review, container, false);
        List<Review> reviewList = getGoogleDefault();
        populateReview(view, reviewList);
        return view;
    }

    private List<Review> getGoogleDefault(){
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
        return reviewList;
    }

    private void populateReview(final View view, final List<Review> reviewList) {

        recyclerView = (RecyclerView)view.findViewById(R.id.recyclerviewreviews);
        RecyclerViewClickListener listener = new RecyclerViewClickListener(){
            @Override
            public void onClick(View view, int position) {
                Review r = mAdapter.getReview(position);
                String url = r.authorURL;

                Uri uriUrl = Uri.parse(url);
                Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
                startActivity(launchBrowser);
            }
        };
        mAdapter = new AdapterReview(getContext(), reviewList, listener);

        recyclerView.setAdapter(mAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        Spinner spinnerSort = (Spinner)view.findViewById(R.id.spinnerorder);

        spinnerSort.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(getContext(), position+"", Toast.LENGTH_SHORT).show();
                switch (position){
                    case 0:
                        mAdapter.data = getGoogleDefault();
                        break;
                    case 1:
                        sortByRating(false, mAdapter.data);
                        break;
                    case 2:
                        sortByRating(true, mAdapter.data);
                        break;
                    case 3:
                        sortByDate(false, mAdapter.data);
                        break;
                    case 4:
                        sortByDate(true, mAdapter.data);
                        break;
                }
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    private void sortByRating(final boolean b, List<Review> reviewList) {
        Collections.sort(reviewList, new Comparator<Review>() {
            @Override
            public int compare(Review o1, Review o2) {
                if(b){
                    return o1.rating - o2.rating;
                }
                else{
                    return o2.rating - o1.rating;
                }
            }
        });
    }

    private void sortByDate(final boolean b, List<Review> reviewList) {
        Collections.sort(reviewList, new Comparator<Review>() {
            @Override
            public int compare(Review o1, Review o2) {
                if(b){
                    return o1.time.compareTo(o2.time);
                }
                else{
                    return o2.time.compareTo(o1.time);
                }
            }
        });
    }

}
