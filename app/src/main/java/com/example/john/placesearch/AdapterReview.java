package com.example.john.placesearch;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class AdapterReview extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private Context context;
    private LayoutInflater inflater;
    private RecyclerViewClickListener adapterListner;
    public List<Review> data;

    // create constructor to innitilize context and data sent from MainActivity
    public AdapterReview(Context context, List<Review> data, RecyclerViewClickListener listener){
        this.context=context;
        inflater= LayoutInflater.from(context);
        this.data=data;
        this.adapterListner = listener;
    }

    public Review getReview(int position){
        return this.data.get(position);
    }

    // Inflate the layout when viewholder created
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.single_review, parent, false);
        MyHolder holder=new MyHolder(view, adapterListner);
        return holder;
    }

    // Bind data
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        // Get current position of item in recyclerview to bind data and assign values from list
        MyHolder myHolder= (MyHolder) holder;
        Review current=data.get(position);

        //set views
        myHolder.textAuthor.setText(current.authorName);
        myHolder.textRating.setText(""+current.rating);
        myHolder.textTime.setText(Review.timeFormat.format(current.time));
        myHolder.textContent.setText(current.content);

        // load image into imageview using glide
        Glide.with(context).load(current.photoURL)
                .into(myHolder.imageView);
    }

    // return total item from List
    @Override
    public int getItemCount() {
        return data.size();
    }


    class MyHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        ImageView imageView;
        TextView textAuthor;
        TextView textRating;
        TextView textTime;
        TextView textContent;
        private RecyclerViewClickListener mListener;

        // create constructor to get widget reference
        public MyHolder(View itemView, RecyclerViewClickListener listener) {
            super(itemView);
            textAuthor = (TextView) itemView.findViewById(R.id.textviewauthorname);
            imageView = (ImageView) itemView.findViewById(R.id.iconprofile);
            textRating = (TextView) itemView.findViewById(R.id.textviewrate);
            textTime = (TextView) itemView.findViewById(R.id.textviewdatetime);
            textContent = (TextView) itemView.findViewById(R.id.textviewcontent);
            itemView.setOnClickListener(this);
            mListener = listener;
        }

        @Override
        public void onClick(View view) {
            mListener.onClick(view, getAdapterPosition());
        }

    }
}
