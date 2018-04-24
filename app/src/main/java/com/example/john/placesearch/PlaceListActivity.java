package com.example.john.placesearch;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class PlaceListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private AdapterPlace mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_list);

        Intent intent = getIntent();
        String message = intent.getStringExtra(SearchFragment.EXTRA_MESSAGE);

        // Capture the layout's TextView and set the string as its text
        parseJson(message);
    }

    protected void parseJson(String result){
        try {
            JSONObject jsonObject = new JSONObject(result);

            //test if the status is okay
            if(!jsonObject.get("status").equals("OK")){
                //do something
            }

            List<Place> placeList = new ArrayList<>();

            JSONArray jsonArray = jsonObject.getJSONArray("results");
            // Extract data from json and store into ArrayList as class objects
            for(int i=0;i<jsonArray.length();i++){
                JSONObject json_data = jsonArray.getJSONObject(i);

                Place place = new Place(json_data.getString("id"),
                                        json_data.getString("name"),
                                        json_data.getString("vicinity"),
                                        json_data.getString("icon"));
                placeList.add(place);
            }

            // Setup and Handover data to recyclerview
            recyclerView = (RecyclerView)findViewById(R.id.placeNearbyList);
            mAdapter = new AdapterPlace(PlaceListActivity.this, placeList);
            recyclerView.setAdapter(mAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(PlaceListActivity.this));

        } catch (JSONException e) {
            Toast.makeText(PlaceListActivity.this, e.toString(), Toast.LENGTH_LONG).show();
        }

    }
}
