package com.labs.mpip.lab2;

import android.content.Context;
import android.content.Intent;
import android.content.pm.LabeledIntent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.labs.mpip.lab2.models.Movie;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<MovieItemViewHolder> {


    private List<Movie> mData;
    private LayoutInflater mInflater;
    private Context context;


    // data is passed into the constructor
    public RecyclerViewAdapter(Context context, List<Movie> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
        this.context = context;
    }

    // inflates the row layout from xml when needed
    @NonNull
    @Override
    public MovieItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.recyclerview_item, parent, false);
        MovieItemViewHolder movieItemViewHolder = new MovieItemViewHolder(view);
        return movieItemViewHolder;
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(@NonNull MovieItemViewHolder holder, int position) {
        final Movie movie = mData.get(position);
        holder.bind(movie);
        holder.getParent().setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, MovieDetailsActivity.class);
                intent.putExtra("imdbID", movie.getImdbID());
                context.startActivity(intent);
            }
        });
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return mData.size();
    }


    public void setmData(List<Movie> movies) {
        this.mData = movies;
    }

    // convenience method for getting data at click position
    Movie getItem(int id) {
        return mData.get(id);
    }

}
