package com.example.john.placesearch;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Layout;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PlaceListActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.john.placesearch.PlaceListActivity.MESSAGE";
    public String example_json = "{\"html_attributions\":[],\"result\":{\"address_components\":[{\"long_name\":\"Los Angeles\",\"short_name\":\"Los Angeles\",\"types\":[\"locality\",\"political\"]},{\"long_name\":\"South Los Angeles\",\"short_name\":\"South Los Angeles\",\"types\":[\"neighborhood\",\"political\"]},{\"long_name\":\"Los Angeles County\",\"short_name\":\"Los Angeles County\",\"types\":[\"administrative_area_level_2\",\"political\"]},{\"long_name\":\"California\",\"short_name\":\"CA\",\"types\":[\"administrative_area_level_1\",\"political\"]},{\"long_name\":\"United States\",\"short_name\":\"US\",\"types\":[\"country\",\"political\"]},{\"long_name\":\"90007\",\"short_name\":\"90007\",\"types\":[\"postal_code\"]}],\"adr_address\":\"Los Angeles<\\/span>, CA<\\/span> 90007<\\/span>, USA<\\/span>\",\"formatted_address\":\"Los Angeles, CA 90007, USA\",\"formatted_phone_number\":\"(213) 740-2311\",\"geometry\":{\"location\":{\"lat\":34.0223519,\"lng\":-118.285117},\"viewport\":{\"northeast\":{\"lat\":34.02825570000001,\"lng\":-118.2819925},\"southwest\":{\"lat\":34.01515809999999,\"lng\":-118.2944905}}},\"icon\":\"https:\\/\\/maps.gstatic.com\\/mapfiles\\/place_api\\/icons\\/school-71.png\",\"id\":\"b85217d74722f6fec94a4135f209e13092d81a5e\",\"international_phone_number\":\"+1 213-740-2311\",\"name\":\"University of Southern California\",\"photos\":[{\"height\":552,\"html_attributions\":[\"Leonard Estrada<\\/a>\"],\"photo_reference\":\"CmRaAAAAS7FkGV8r3jzhQSWuEdyy4E9yw58L9ZPuo9RKOSyIZWhlQVDP2DmX55vSvRXo6Oi8kFr9o5vf3vVtgVvn2FF7FFKXRY_YEkaQPbO0FLZxepNEx8RbehoH9uF5_HARLE6REhBctceBYO2rGxwfKqXb5Ay1GhTOZ2VFy_qO3w9Y9ij6DB3Va4JFxw\",\"width\":750,\"link\":\"img\\/CmRaAAAAS7FkGV8r3jzhQSWuEdyy4E9yw58L9ZPuo9RKOSyIZWhlQVDP2DmX55vSvRXo6Oi8kFr9o5vf3vVtgVvn2FF7FFKXRY_YEkaQPbO0FLZxepNEx8RbehoH9uF5_HARLE6REhBctceBYO2rGxwfKqXb5Ay1GhTOZ2VFy_qO3w9Y9ij6DB3Va4JFxw.jpg\"},{\"height\":1500,\"html_attributions\":[\"Terrell Woods<\\/a>\"],\"photo_reference\":\"CmRaAAAAqeaIEO0bzdPsLUhaW83g89Kbojt-gVnLOQLVocDKe9hxE1HIeJNlEEeKwxA_XEu92KTjD0kU_q_WixFzPDKQ1fchomY70H4PMRpR8stdfF97kkYR72VylUWpXdgZ5CpGEhDjLixpZV0j2yVwzgdhbGQhGhTapwB99E3bDJqoCxUJIGsXpfR8Qw\",\"width\":2000,\"link\":\"img\\/CmRaAAAAqeaIEO0bzdPsLUhaW83g89Kbojt-gVnLOQLVocDKe9hxE1HIeJNlEEeKwxA_XEu92KTjD0kU_q_WixFzPDKQ1fchomY70H4PMRpR8stdfF97kkYR72VylUWpXdgZ5CpGEhDjLixpZV0j2yVwzgdhbGQhGhTapwB99E3bDJqoCxUJIGsXpfR8Qw.jpg\"},{\"height\":773,\"html_attributions\":[\"Rd Yab<\\/a>\"],\"photo_reference\":\"CmRaAAAAIt3xgKUu_m3U2WEmlFfXJK2hKeMO2ut3jIjasAxvTNBd6mnLgFCq4NaomLvZVmqp649Zk_x90aei1fgtaSp9gExTmdalpQcxkRwTUcgTLKYLbzkZTpv3_j5Bb2JXCNN2EhCQMKgH6Y1aBcvzyp-LOZNuGhQf-uyqnVWelUO1BZGzHTsKTptIlQ\",\"width\":990,\"link\":\"img\\/CmRaAAAAIt3xgKUu_m3U2WEmlFfXJK2hKeMO2ut3jIjasAxvTNBd6mnLgFCq4NaomLvZVmqp649Zk_x90aei1fgtaSp9gExTmdalpQcxkRwTUcgTLKYLbzkZTpv3_j5Bb2JXCNN2EhCQMKgH6Y1aBcvzyp-LOZNuGhQf-uyqnVWelUO1BZGzHTsKTptIlQ.jpg\"},{\"height\":5248,\"html_attributions\":[\"Aref Shafaei<\\/a>\"],\"photo_reference\":\"CmRaAAAATOiZ94TLYB5loI3AzwM_nfo6q4sk1QfDT5nwX_uJbxjLHynUPYMW4UsE8gJPb7d1dILGKAaZYnHdyI0SAT4qEK1eJDMzw_vf6uh2_-kolfDtMmJQj7u_U6yeWDTVQfXGEhDDGCRniKy4Nrbi3PHIA9yNGhTzGiD1x_qQCzuJ1PTPIu2t-jR9AA\",\"width\":3936,\"link\":\"img\\/CmRaAAAATOiZ94TLYB5loI3AzwM_nfo6q4sk1QfDT5nwX_uJbxjLHynUPYMW4UsE8gJPb7d1dILGKAaZYnHdyI0SAT4qEK1eJDMzw_vf6uh2_-kolfDtMmJQj7u_U6yeWDTVQfXGEhDDGCRniKy4Nrbi3PHIA9yNGhTzGiD1x_qQCzuJ1PTPIu2t-jR9AA.jpg\"},{\"height\":2448,\"html_attributions\":[\"Corwag Entertainment<\\/a>\"],\"photo_reference\":\"CmRaAAAAOVL8NpSjff8Pzi1T_if-098BwfwIWE7FQTdrfBAUeyAXr8sGuYj09kpZ_1MaxlVroK4_8SCd296rtmvfDBgeQCZBNA2Wo9ZMchRN2XpiadvjNDhuJ45PG1H2Fb32Ju2ZEhAoITb6Bs3aeiiD0QvV3h20GhQ_hqihpvHIO8rXzrkdirIBVCiwZA\",\"width\":3264,\"link\":\"img\\/CmRaAAAAOVL8NpSjff8Pzi1T_if-098BwfwIWE7FQTdrfBAUeyAXr8sGuYj09kpZ_1MaxlVroK4_8SCd296rtmvfDBgeQCZBNA2Wo9ZMchRN2XpiadvjNDhuJ45PG1H2Fb32Ju2ZEhAoITb6Bs3aeiiD0QvV3h20GhQ_hqihpvHIO8rXzrkdirIBVCiwZA.jpg\"},{\"height\":2268,\"html_attributions\":[\"KYLE<\\/a>\"],\"photo_reference\":\"CmRaAAAAYgay3Om9rE5UQ2d7vS4TYC-lU7kNyDjXZBwn8ajMZlznBeZZQ1GTNyfgMb0vAB2l6MEZs_Xy3ww-pABurvXNY_1SiTS_JolwPEuh3AU2q1yrxvTcAhkNL3bJKp8JFTGpEhCCKt3s-De6zqOaXQaM8-2OGhRqmZDQgvk3eteZeJ73z_bNluxyvQ\",\"width\":4032},{\"height\":1200,\"html_attributions\":[\"Rd Yab<\\/a>\"],\"photo_reference\":\"CmRaAAAAf4Np_A11aS_taSEaHIr1rRFt5hjbTGH6lhDvkWlrppmGRZnU8xgmQdnfbpJdVVVk-H5-xsppE1ymab-TvrBXShBDeHZxxEdsFmFITJIZcCrVt_mw-kGpTsIWFMLv_pBaEhDkiUvhObEOtnd647UToxDbGhQlDV0FiIQ9sjIDiSK7rkQiBeGn1Q\",\"width\":1800},{\"height\":3264,\"html_attributions\":[\"Rucha<\\/a>\"],\"photo_reference\":\"CmRaAAAAQsile0KrxjG5T-OXg9aJHb9od974cJgQ3RUgM_iwlpwJP2Xb7BNN9silL1HfqBsh-02x9FTCZH9RF0wzhJ_OUeTe2Mis9egYV67EKm5VCPWaeMH4Ioq-wcrCVRXYVytgEhDkfBjWqrMkwN-m20RcYbQ4GhTSK1tq3Py_sgpD4qo06Cc-V6x2_w\",\"width\":2448},{\"height\":2448,\"html_attributions\":[\"Rucha<\\/a>\"],\"photo_reference\":\"CmRaAAAAwWd7ukHi_-_I_l2pwuOjwy8fBQyfIAieamxeAhJQN3aVX-iLM_GMfknq09rGRV9NYqFSmMIc5gCG-nr1TGTdnoJt6mrQme2b5ZBlPhvTMknY4kyMK42TniprbZG-8tlVEhBd4kBT5Bjc1Z4kS2L3qOToGhQaSYkAxpS7GFdyU9S0EXL7FOcG-Q\",\"width\":3264},{\"height\":517,\"html_attributions\":[\"\\u65c5\\u6e38\\u73af\\u7403<\\/a>\"],\"photo_reference\":\"CmRaAAAAL1lhwz7acEXq7uDSnxPRZjVb1v-malUvM0xkTNxuIEGUUDZ9Nx9uzGXTVEjLTNC1LABQU7ZrHliFcZTIjnfyIKl0bHa6rYeLz57omeyjTeIWTHFOy3Wfmb8KRvpEMiGIEhDw6Sr0OYcSow3ENMWseB0UGhS-Q1yuo6msuBholJ70w5mqKlCj_g\",\"width\":690}],\"place_id\":\"ChIJ7aVxnOTHwoARxKIntFtakKo\",\"price_level\":2,\"rating\":4.5,\"reference\":\"CmRSAAAA3mfXBg5qSDLLbbJcxLisDm6Z-jASJj2mlNHrSFqphLEe-BTHvXg7dXV0Nv0O7kNP0f69jhltCCezCeTFE8msDOhFh5qVFywmhQzyuRNJYXQzr4hVdW_AaPZSDjOeC_n6EhAK5fZDh3786wy8O-Pc-SssGhQlxnojDyhrK5_t5iRuLhvI3ZGlwg\",\"reviews\":[{\"author_name\":\"William Dencker\",\"author_url\":\"https:\\/\\/www.google.com\\/maps\\/contrib\\/104691566940250346110\\/reviews\",\"language\":\"en\",\"profile_photo_url\":\"https:\\/\\/lh3.googleusercontent.com\\/-tlOK62BI-vE\\/AAAAAAAAAAI\\/AAAAAAAAADw\\/CCYN95aoMoU\\/s128-c0x00000000-cc-rp-mo\\/photo.jpg\",\"rating\":5,\"relative_time_description\":\"a month ago\",\"text\":\"I will go here to obtain friends through conversation and socializing effectively. I have high hopes that I will have ones to converse with, thank you for reading this critical review in my lifespan. I may be 4 but I believe I can get into this school by the age of 9 easily.\",\"time\":1521225870},{\"author_name\":\"bob vance\",\"author_url\":\"https:\\/\\/www.google.com\\/maps\\/contrib\\/102173223332728283965\\/reviews\",\"language\":\"en\",\"profile_photo_url\":\"https:\\/\\/lh3.googleusercontent.com\\/-RT5qPmgIu_s\\/AAAAAAAAAAI\\/AAAAAAAAAAA\\/ACLGyWDt4oIcpXE_7CYWdwVCqOXetTBTiA\\/s128-c0x00000000-cc-rp-mo\\/photo.jpg\",\"rating\":4,\"relative_time_description\":\"a week ago\",\"text\":\"Large campus. Like the design and layout. The ambassadors will point you in the right direction if you get lost. Fight on\",\"time\":1523743240},{\"author_name\":\"Ambar Pal\",\"author_url\":\"https:\\/\\/www.google.com\\/maps\\/contrib\\/105529244132951202177\\/reviews\",\"language\":\"en\",\"profile_photo_url\":\"https:\\/\\/lh3.googleusercontent.com\\/-xNk-Ah0clEs\\/AAAAAAAAAAI\\/AAAAAAAAAI8\\/zjxorSYh8jQ\\/s128-c0x00000000-cc-rp-mo\\/photo.jpg\",\"rating\":5,\"relative_time_description\":\"4 months ago\",\"text\":\"I had a great great time here. The campus is quite intricately made, with all the carvings on the walls and the architecture. The Trojan spirit captivates you are you roam around the campus. There is a huge football field, and also running tracks, swimming pools. The Doheny Library is one of a kind of it's own. Overall, this is an amazing place to be.\",\"time\":1512277072},{\"author_name\":\"Victorree Lett\",\"author_url\":\"https:\\/\\/www.google.com\\/maps\\/contrib\\/108087243859738643710\\/reviews\",\"language\":\"en\",\"profile_photo_url\":\"https:\\/\\/lh3.googleusercontent.com\\/-PhXAhiaQQZU\\/AAAAAAAAAAI\\/AAAAAAAAAM4\\/Fa5ziCJ_g_U\\/s128-c0x00000000-cc-rp-mo\\/photo.jpg\",\"rating\":5,\"relative_time_description\":\"a week ago\",\"text\":\"This place is great my dad worked here as a art teacher.\\ud83d\\udc4d\\ud83c\\udffe\\ud83d\\ude07\\ud83d\\ude07\\ud83d\\udc4c\\ud83c\\udffe\\ud83d\\udc69\\ud83c\\udffe\\u200d\\ud83c\\udfeb\\ud83d\\udc69\\ud83c\\udffe\\u200d\\ud83c\\udfeb\",\"time\":1523907686},{\"author_name\":\"Richard Baal\",\"author_url\":\"https:\\/\\/www.google.com\\/maps\\/contrib\\/108521383992417406868\\/reviews\",\"language\":\"en\",\"profile_photo_url\":\"https:\\/\\/lh3.googleusercontent.com\\/-3uvO2gMFSe8\\/AAAAAAAAAAI\\/AAAAAAAAAAw\\/wZ0_stymTds\\/s128-c0x00000000-cc-rp-mo\\/photo.jpg\",\"rating\":3,\"relative_time_description\":\"2 weeks ago\",\"text\":\"The campus is nice inside but it looks like prison from outside because its in the ghetto parts of LA\",\"time\":1522983362}],\"scope\":\"GOOGLE\",\"types\":[\"university\",\"point_of_interest\",\"establishment\"],\"url\":\"https:\\/\\/maps.google.com\\/?cid=12290422733005103812\",\"utc_offset\":-420,\"vicinity\":\"Los Angeles\",\"website\":\"http:\\/\\/usc.edu\\/\"},\"status\":\"OK\"}";

    public RecyclerView recyclerView;
    private AdapterPlace mAdapter;
    private Stack<List<Place>> previousPageStack;
    private Stack<List<Place>> nextPageStack;
    private String nextPageToken = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_list);

        //tool bar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar3);
        toolbar.setTitle("Search Results");


        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed(); // Implemented by activity
            }
        });
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Intent intent = getIntent();
        String message = intent.getStringExtra(SearchFragment.EXTRA_MESSAGE);

        previousPageStack = new Stack<>();
        nextPageStack = new Stack<>();

        //set button listener
        Button buttonPrevious = (Button) findViewById(R.id.buttonprevious);
        buttonPrevious.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                if(!previousPageStack.empty()){
                    nextPageStack.push(mAdapter.data);
                    mAdapter.data = previousPageStack.pop();
                    mAdapter.notifyDataSetChanged();

                    setButtonEnable();
                }
            }
        });

        //set button listener
        Button buttonNext = (Button) findViewById(R.id.buttonnext);
        buttonNext.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                if(!nextPageStack.empty()){
                    previousPageStack.push(mAdapter.data);
                    mAdapter.data = nextPageStack.pop();
                    mAdapter.notifyDataSetChanged();

                    setButtonEnable();
                }
                else if(nextPageToken != null){
                    previousPageStack.push(mAdapter.data);
                    searchNextPage(nextPageToken);
                }

            }
        });

        // Capture the layout's TextView and set the string as its text
        List<Place> placeList = parseJson(message);

        if(placeList.size() == 0){
            LinearLayout layout =  (LinearLayout)findViewById(R.id.layoutsearchlist);
            RelativeLayout textError =  (RelativeLayout)findViewById(R.id.layouterror);
            layout.setVisibility(View.GONE);
            textError.setVisibility(View.VISIBLE);
        }
        else{
            LinearLayout layout =  (LinearLayout)findViewById(R.id.layoutsearchlist);
            RelativeLayout textError =  (RelativeLayout)findViewById(R.id.layouterror);
            layout.setVisibility(View.VISIBLE);
            textError.setVisibility(View.GONE);
        }

        // Setup and Handover data to recyclerview
        recyclerView = (RecyclerView)findViewById(R.id.placeNearbyList);
        RecyclerViewClickListener listener = new RecyclerViewClickListener(){
                @Override
                public void onClick(View view, int position) {
                    Place place = mAdapter.getPlace(position);
//                Toast.makeText(PlaceListActivity.this, place.id, Toast.LENGTH_SHORT).show();
                    searchAndGo(place.id);
                }
            };
            mAdapter = new AdapterPlace(PlaceListActivity.this, placeList, listener);

            recyclerView.setAdapter(mAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(PlaceListActivity.this));

            //Set up button enable
            setButtonEnable();


    }

    @Override
    protected void onStart() {
        super.onStart();
        mAdapter.notifyDataSetChanged();
    }

    private void setButtonEnable() {
        Button buttonPrevious = (Button) findViewById(R.id.buttonprevious);
        Button buttonNext = (Button) findViewById(R.id.buttonnext);
        if(!previousPageStack.empty()){
            buttonPrevious.setEnabled(true);
        }
        else{
            buttonPrevious.setEnabled(false);
        }
        if(!nextPageStack.empty() || nextPageToken != null){
            buttonNext.setEnabled(true);
        }
        else{
            buttonNext.setEnabled(false);
        }

    }

    private void searchAndGo(String placeID) {

        final ProgressDialog progress = new ProgressDialog(this);
        progress.setMessage("Fetching results");
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="http://homework8.us-west-2.elasticbeanstalk.com/api/detail/"+placeID;

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progress.dismiss();
                        navigateToNext(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //
            }
        });
        progress.show();
        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }

    private void searchNextPage(String nextToken) {

        final ProgressDialog progress = new ProgressDialog(this);
        progress.setMessage("Fetching results");
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="http://homework8.us-west-2.elasticbeanstalk.com/api/nextPage/"+nextToken;

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progress.dismiss();
                        List<Place> placeList = parseJson(response);
                        mAdapter.data = placeList;
                        mAdapter.notifyDataSetChanged();
                        setButtonEnable();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //
            }
        });
        progress.show();
        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }

    protected List<Place> parseJson(String result){
        List<Place> placeList = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(result);

            //test if the status is okay
            if(!jsonObject.get("status").equals("OK")){
                return placeList;
            }

            JSONArray jsonArray = jsonObject.getJSONArray("results");
            // Extract data from json and store into ArrayList as class objects
            for(int i=0;i<jsonArray.length();i++){
                JSONObject json_data = jsonArray.getJSONObject(i);

                Place place = new Place(json_data.getString("place_id"),
                                        json_data.getString("name"),
                                        json_data.getString("vicinity"),
                                        json_data.getString("icon"));
                placeList.add(place);
            }

            nextPageToken = jsonObject.getString("next_page_token");

        } catch (JSONException e) {
            Toast.makeText(PlaceListActivity.this, e.toString(), Toast.LENGTH_LONG).show();
            nextPageToken = null;
        }
        return placeList;
    }

    private void navigateToNext(String message) {
        Intent intent = new Intent(PlaceListActivity.this, PlaceDetailActivity.class);
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }


}
