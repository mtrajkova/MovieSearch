package com.labs.mpip.lab2;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.labs.mpip.lab2.clients.OmdbAPI;
import com.labs.mpip.lab2.models.Movie;
import com.labs.mpip.lab2.models.MovieResponse;
import com.labs.mpip.lab2.persistance.MovieDatabase;
import com.labs.mpip.lab2.persistance.repository.MovieRepository;
import com.labs.mpip.lab2.services.OmdbApiService;
import com.labs.mpip.lab2.viewmodels.MovieDetailsViewModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieDetailsActivity extends AppCompatActivity {
    TextView movieDetails;
    Bundle extras;
    String imdbId;
    Movie movie;
    MovieDetailsViewModel movieDetailsViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        movieDetails = findViewById(R.id.tvMovieDetails);
        extras = getIntent().getExtras();
        movieDetailsViewModel = ViewModelProviders.of(this).get(MovieDetailsViewModel.class);


        if (extras != null) {
            imdbId = extras.getString("imdbID");
            movieDetailsViewModel.setMovie(imdbId);
             movieDetailsViewModel.getMovie().observe(this, new Observer<Movie>() {
                @Override
                public void onChanged(@Nullable Movie movie) {
                    movieDetails.setText(movie.getImdbID());
                }
            });

        }

    }

}
