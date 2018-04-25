package com.example.john.placesearch;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.location.places.GeoDataClient;
import com.google.android.gms.location.places.Places;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class PlaceDetailActivity extends AppCompatActivity {
    private JSONObject contextJson;
    private GeoDataClient mGeoDataClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_detail);

        //get intent
        Intent intent = getIntent();
        String message = intent.getStringExtra(PlaceListActivity.EXTRA_MESSAGE);

        // Capture the layout's TextView and set the string as its text
        contextJson = parseJson(message);

        // Construct a GeoDataClient.
        mGeoDataClient = Places.getGeoDataClient(this, null);

        //tool bar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);

        // Create an instance of the tab layout from the view.
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout2);
        // Set the text for each tab.
        tabLayout.addTab(tabLayout.newTab().setText(R.string.info_tab));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.photo_tab));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.map_tab));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.review_tab));
        // Set the tabs to fill the entire layout.
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        // Using PagerAdapter to manage page views in fragments.
        // Each page is represented by its own fragment.
        // This is another example of the adapter pattern.
        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager2);

        final DetailPagerAdpater adapter = new DetailPagerAdpater
                (getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);

        // Setting a listener for clicks.
        viewPager.addOnPageChangeListener(new
                TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });

        //receive json message

    }

    protected JSONObject parseJson(String result){
        JSONObject res = null;
        try {
            JSONObject jsonObject = new JSONObject(result);

            //test if the status is okay
            if(!jsonObject.get("status").equals("OK")){
                //do something
            }
            res = jsonObject.getJSONObject("result");

        }
        catch (JSONException e) {
            Toast.makeText(PlaceDetailActivity.this, e.toString(), Toast.LENGTH_LONG).show();
        }
        return res;
    }

    public JSONObject getContextJson() {
        return contextJson;
    }

    public GeoDataClient getmGeoDataClient() {
        return mGeoDataClient;
    }
}
