package com.example.john.placesearch;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;

import static java.security.AccessController.getContext;

public class AdapterPlace extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private Context context;
    private LayoutInflater inflater;
    private RecyclerViewClickListener adapterListner;
    public List<Place> data;
    Place current;
    int currentPos=0;

    // create constructor to innitilize context and data sent from MainActivity
    public AdapterPlace(Context context, List<Place> data, RecyclerViewClickListener listener){
        this.context=context;
        inflater= LayoutInflater.from(context);
        this.data=data;
        this.adapterListner = listener;
    }

    public Place getPlace(int position){
        return this.data.get(position);
    }

    // Inflate the layout when viewholder created
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.place_concise, parent, false);
        MyHolder holder=new MyHolder(view, adapterListner);
        return holder;
    }

    // Bind data
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        // Get current position of item in recyclerview to bind data and assign values from list
        MyHolder myHolder= (MyHolder) holder;
        Place current=data.get(position);
        myHolder.textName.setText(current.name);
        myHolder.textAddress.setText(current.address);
        if(FavouritePlaces.isFavourite(current.id)){
            myHolder.favButton.setImageResource(R.drawable.heart_fill_red);
        }
        else{
            myHolder.favButton.setImageResource(R.drawable.heart_fill_white);
        }
        // load image into imageview using glide
        Glide.with(context).load(current.icon)
                .into(myHolder.imageView);

    }

    // return total item from List
    @Override
    public int getItemCount() {
        return data.size();
    }


    class MyHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        ImageView imageView;
        TextView textName;
        TextView textAddress;
        ImageButton favButton;
        private RecyclerViewClickListener mListener;

        // create constructor to get widget reference
        public MyHolder(View itemView, RecyclerViewClickListener listener) {
            super(itemView);
            textName = (TextView) itemView.findViewById(R.id.textName);
            imageView = (ImageView) itemView.findViewById(R.id.iconcategory);
            textAddress = (TextView) itemView.findViewById(R.id.textAddress);
            favButton = (ImageButton) itemView.findViewById(R.id.imagebuttonfav);
            favButton.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v) {
                    Place p = getPlace(getAdapterPosition());
                    if(FavouritePlaces.isFavourite(p.id)){
//                        ((ImageButton)v).setImageResource(R.drawable.heart_fill_white);
                        FavouritePlaces.removeFromFavouritePlaces(p.id);
                    }
                    else{
//                        ((ImageButton)v).setImageResource(R.drawable.heart_fill_red);
                        Toast.makeText(context, p.name + "was added to favourite", Toast.LENGTH_SHORT).show();
                        FavouritePlaces.addToFavouritePlaces(p);
                    }
                    notifyDataSetChanged();
                }
            });
            itemView.setOnClickListener(this);
            mListener = listener;
        }

        @Override
        public void onClick(View view) {
            mListener.onClick(view, getAdapterPosition());
        }

    }
}
