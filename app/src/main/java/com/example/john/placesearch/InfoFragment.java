package com.example.john.placesearch;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;


/**
 * A simple {@link Fragment} subclass.
 */
public class InfoFragment extends Fragment {


    public InfoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_info, container, false);
        populateInfo(v);
        return v;
    }

    private void populateInfo(View v) {
        JSONObject jsonObj = ((PlaceDetailActivity)getActivity()).getContextJson();
        try{
            ((TextView)v.findViewById(R.id.textviewaddress)).setText(jsonObj.getString("formatted_address"));
            ((TextView)v.findViewById(R.id.textviewphone)).setText(jsonObj.getString("formatted_phone_number"));
            ((TextView)v.findViewById(R.id.textviewprice)).setText( "$$$$$".substring(0, jsonObj.getInt("price_level")) );
            ((TextView)v.findViewById(R.id.textviewrating)).setText(jsonObj.getString("rating"));
            ((TextView)v.findViewById(R.id.textviewgooglepage)).setText(jsonObj.getString("url"));
            ((TextView)v.findViewById(R.id.textviewwebsite)).setText(jsonObj.getString("website"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
