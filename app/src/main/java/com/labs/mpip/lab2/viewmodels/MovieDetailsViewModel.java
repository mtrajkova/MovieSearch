package com.labs.mpip.lab2.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.labs.mpip.lab2.models.Movie;
import com.labs.mpip.lab2.persistance.MovieDatabase;
import com.labs.mpip.lab2.persistance.dao.MovieDao;

public class MovieDetailsViewModel extends AndroidViewModel {

    private MovieDao movieDao;
    private LiveData<Movie> movie;

    public MovieDetailsViewModel(@NonNull Application application) {
        super(application);
        movieDao = MovieDatabase.getMovieDatabase(application.getApplicationContext()).movieDao();
    }

    public void setMovie(String id){
        movie = movieDao.getMovieById(id);
    }

    public LiveData<Movie> getMovie(){
        return movie;
    }
}
