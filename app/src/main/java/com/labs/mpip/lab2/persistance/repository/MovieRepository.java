package com.labs.mpip.lab2.persistance.repository;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.os.AsyncTask;

import com.labs.mpip.lab2.models.Movie;
import com.labs.mpip.lab2.persistance.MovieDatabase;

import java.util.List;

public class MovieRepository {

    private  String DB_NAME = "movie_items";

    private  MovieDatabase movieDatabase;

    private Context context;

    public MovieRepository(Context context) {
        movieDatabase = Room.databaseBuilder(context, MovieDatabase.class, DB_NAME).build();
    }

    public void insertItem(final Movie movie) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                movieDatabase.movieDao().insert(movie);
                return null;
            }

        }.execute();
    }

    public LiveData<List<Movie>> listAllMovies() {
        return movieDatabase.movieDao().getMovies();
    }

    public LiveData<Movie> getMovieById(String id) {
        return movieDatabase.movieDao().getMovieById(id);
    }

    public void deleteAll() {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                movieDatabase.movieDao().deleteAll();
                return null;
            }
        }.execute();
    }
}
