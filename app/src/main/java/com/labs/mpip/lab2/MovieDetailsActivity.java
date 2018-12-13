package com.labs.mpip.lab2;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.labs.mpip.lab2.models.Movie;
import com.labs.mpip.lab2.persistance.MovieDatabase;
import com.labs.mpip.lab2.persistance.repository.MovieRepository;

public class MovieDetailsActivity extends AppCompatActivity {
    TextView movieDetails;
    Bundle extras;
    String imdbId;
    Movie movie;
    MovieRepository movieRepository;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        movieDetails = findViewById(R.id.tvMovieDetails);
        extras = getIntent().getExtras();
        movieRepository =new MovieRepository(getApplicationContext());

        if (extras != null) {
            imdbId = extras.getString("imdbId");
            movie = movieRepository.getMovieById(imdbId).getValue();
            if (movie == null) {
                movieDetails.setText("NOOO");
            } else
                movieDetails.setText("Title: " + movie.getTitle());

        }

        displayDetails();
    }

    public void displayDetails() {
    }
}
