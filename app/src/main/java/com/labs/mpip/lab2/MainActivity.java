package com.labs.mpip.lab2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.labs.mpip.lab2.clients.OmdbAPI;
import com.labs.mpip.lab2.models.Movie;
import com.labs.mpip.lab2.models.MovieResponse;
import com.labs.mpip.lab2.persistance.MovieDatabase;
import com.labs.mpip.lab2.persistance.repository.MovieRepository;
import com.labs.mpip.lab2.services.OmdbApiService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity implements RecyclerViewAdapter.ItemClickListener {

    RecyclerViewAdapter adapter;
    List<Movie> movies = new ArrayList<>();
    RecyclerView recyclerView;
    MovieRepository movieRepository;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        movieRepository = new MovieRepository(getApplicationContext());

        recyclerView = findViewById(R.id.rvMovies);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RecyclerViewAdapter(this, movies);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);

        OmdbApiService service = OmdbAPI.getRetrofit().create(OmdbApiService.class);
        service.getMovies("hulk", "7e25ed24").enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                if (response.isSuccessful()) {
                    movies.addAll(response.body().getMovies());
                    adapter.setmData(movies);
                    recyclerView.setAdapter(adapter);

                    movieRepository.deleteAll();
                    for(Movie movie : movies){
                        movieRepository.insertItem(movie);
                    }
                    List<Movie> ms = movieRepository.listAllMovies().getValue();
                }
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {

            }
        });


        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                new LinearLayoutManager(this).getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);
    }

    @Override
    public void onItemClick(View view, int position) {
//        Toast.makeText(this, "You clicked " + adapter.getItem(position) + " on row number " + position, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, MovieDetailsActivity.class);
        intent.putExtra("imdbId",adapter.getItem(position).imdbID);
        startActivity(intent);
    }
}
