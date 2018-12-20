package com.labs.mpip.lab2;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.labs.mpip.lab2.models.Movie;

public class MovieItemViewHolder extends RecyclerView.ViewHolder {

    private ImageView imageView;
    private TextView textViewTitle;
    private TextView textViewYear;
    private View parent;

    public MovieItemViewHolder(@NonNull View itemView) {
        super(itemView);
        this.imageView = (ImageView) itemView.findViewById(R.id.ivPoster);
        this.textViewTitle = (TextView) itemView.findViewById(R.id.tvTitle);
        this.textViewYear = (TextView) itemView.findViewById(R.id.tvYear);
        this.parent = itemView;
    }

    public void bind(final Movie movie){
        Glide.with(itemView.getContext())
                .load(movie.getPoster())
                .into(getImageView());
        textViewYear.setText(movie.getYear());
        textViewTitle.setText(movie.getTitle());
    }

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    public TextView getTextViewTitle() {
        return textViewTitle;
    }

    public void setTextViewTitle(TextView textViewTitle) {
        this.textViewTitle = textViewTitle;
    }

    public TextView getTextViewYear() {
        return textViewYear;
    }

    public void setTextViewYear(TextView textViewYear) {
        this.textViewYear = textViewYear;
    }

    public View getParent() {
        return parent;
    }

    public void setParent(View parent) {
        this.parent = parent;
    }
}
