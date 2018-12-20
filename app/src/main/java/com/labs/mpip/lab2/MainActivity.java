package com.labs.mpip.lab2;

import android.app.SearchManager;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.SearchView;

import com.labs.mpip.lab2.clients.OmdbAPI;
import com.labs.mpip.lab2.models.Movie;
import com.labs.mpip.lab2.models.MovieResponse;
import com.labs.mpip.lab2.persistance.MovieDatabase;
import com.labs.mpip.lab2.persistance.repository.MovieRepository;
import com.labs.mpip.lab2.services.OmdbApiService;
import com.labs.mpip.lab2.viewmodels.MovieViewModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {

    private RecyclerViewAdapter adapter;
    private List<Movie> movies = new ArrayList<>();
    private RecyclerView recyclerView;
    private MovieViewModel movieViewModel;
    private String query;
    private OmdbApiService service;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Intent.ACTION_SEARCH.equals(getIntent().getAction())) {
            query = getIntent().getStringExtra(SearchManager.QUERY);
        }

//        recyclerView = findViewById(R.id.rvMovies);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        adapter = new RecyclerViewAdapter(this, movies);
//        recyclerView.setAdapter(adapter);

        adapter = new RecyclerViewAdapter(this, movies);
        recyclerView = (RecyclerView) findViewById(R.id.rvMovies);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        service = OmdbAPI.getRetrofit().create(OmdbApiService.class);
        movieViewModel = ViewModelProviders.of(this).get(MovieViewModel.class);

        movieViewModel.getMovies().observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(@Nullable List<Movie> movies) {
                if(movies!=null)
                    adapter.setmData(movies);
            }
        });

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                new LinearLayoutManager(this).getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);
    }


    public void getData(String query){
        service.getMovies(query, "7e25ed24").enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                if (response.isSuccessful()) {
                    movies.addAll(response.body().getMovies());
                    adapter.setmData(movies);
//                    recyclerView.setAdapter(adapter);

                    movieViewModel.deleteAll();
                    for (Movie movie : movies) {
                        movieViewModel.insert(movie);
                    }
                }
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        final SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchView.clearFocus();
                getData(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return true;
    }
}

