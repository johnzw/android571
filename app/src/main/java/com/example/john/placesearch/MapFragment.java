package com.example.john.placesearch;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONException;
import org.json.JSONObject;


/**
 * A simple {@link Fragment} subclass.
 */
public class MapFragment extends Fragment implements OnMapReadyCallback {


    public MapFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_map, container, false);
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.fragmentmap);
        mapFragment.getMapAsync(MapFragment.this);

        final AutoCompleteTextView autoCompleteTextView = (AutoCompleteTextView)view.findViewById(R.id.autoCompleteTextViewforMap);
        final CustomAutoCompleteAdapter autoCompleteAdapter = new CustomAutoCompleteAdapter(getContext());
        autoCompleteTextView.setAdapter(autoCompleteAdapter);
        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                autoCompleteTextView.setText(""+parent.getItemAtPosition(position));
            }
        });

        return view;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        JSONObject jsonObj = ((PlaceDetailActivity)getActivity()).getContextJson();
        float lat = (float) -33.8661;
        float lng = (float) 151.195827;
        try {
            lat = (float) jsonObj.getJSONObject("geometry").getJSONObject("location").getDouble("lat");
            lng = (float) jsonObj.getJSONObject("geometry").getJSONObject("location").getDouble("lat");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        CameraUpdate c = CameraUpdateFactory.newLatLng(new LatLng(lat, lng));

        googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(lat,lng))
                .title("Marker"));

        googleMap.moveCamera(c);

    }
}
