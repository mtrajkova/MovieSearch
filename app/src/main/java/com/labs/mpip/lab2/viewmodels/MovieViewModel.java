package com.labs.mpip.lab2.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.labs.mpip.lab2.async.DeleteMoviesAsyncTask;
import com.labs.mpip.lab2.async.InsertMovieAsyncTask;
import com.labs.mpip.lab2.models.Movie;
import com.labs.mpip.lab2.persistance.MovieDatabase;
import com.labs.mpip.lab2.persistance.dao.MovieDao;

import java.util.List;

public class MovieViewModel extends AndroidViewModel {

    private MovieDao movieDao;
    private LiveData<List<Movie>> movies;

    public MovieViewModel(@NonNull Application application) {
        super(application);
        movieDao = MovieDatabase.getMovieDatabase(application.getApplicationContext()).movieDao();
        movies = movieDao.getMovies();
    }

    public void insert(Movie movie){
        new InsertMovieAsyncTask(movieDao).execute(movie);
    }

    public void deleteAll(){
        new DeleteMoviesAsyncTask(movieDao).execute();
    }

    public LiveData<List<Movie>> getMovies(){
        return movies;
    }
}
