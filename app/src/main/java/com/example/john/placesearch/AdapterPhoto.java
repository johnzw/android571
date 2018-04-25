package com.example.john.placesearch;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.location.places.PlacePhotoMetadata;
import com.google.android.gms.location.places.PlacePhotoMetadataBuffer;
import com.google.android.gms.location.places.PlacePhotoResponse;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.util.List;

public class AdapterPhoto extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private Context context;
    private LayoutInflater inflater;
    private int size;
    PlacePhotoMetadataBuffer photoMetadataBuffer;

    // create constructor to innitilize context and data sent from MainActivity
    public AdapterPhoto(Context context,PlacePhotoMetadataBuffer photoMetadataBuffer){
        this.context=context;
        inflater= LayoutInflater.from(context);
        this.photoMetadataBuffer=photoMetadataBuffer;
        this.size = photoMetadataBuffer.getCount();
    }

    // Inflate the layout when viewholder created
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.single_photo, parent, false);
        MyHolder holder=new MyHolder(view);
        return holder;
    }

    // Bind data
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        // Get current position of item in recyclerview to bind data and assign values from list
        final MyHolder myHolder= (MyHolder) holder;

        PlacePhotoMetadata photoMetadata = photoMetadataBuffer.get(position);

        // Get the attribution text.
        CharSequence attribution = photoMetadata.getAttributions();
        // Get a full-size bitmap for the photo.
        Task<PlacePhotoResponse> photoResponse = ((PlaceDetailActivity)this.context).getmGeoDataClient().getPhoto(photoMetadata);

        photoResponse.addOnCompleteListener(new OnCompleteListener<PlacePhotoResponse>() {
            @Override
            public void onComplete(@NonNull Task<PlacePhotoResponse> task) {

                PlacePhotoResponse photo = task.getResult();
                Bitmap bitmap = photo.getBitmap();
                myHolder.imageView.setImageBitmap(bitmap);
            }
        });
    }

    // return total item from List
    @Override
    public int getItemCount() {
        return this.size;
    }

    class MyHolder extends RecyclerView.ViewHolder{

        ImageView imageView;

        // create constructor to get widget reference
        public MyHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.imageViewPhoto);
        }
    }
}
