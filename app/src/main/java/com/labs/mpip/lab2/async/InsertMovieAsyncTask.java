package com.labs.mpip.lab2.async;

import android.os.AsyncTask;

import com.labs.mpip.lab2.models.Movie;
import com.labs.mpip.lab2.persistance.dao.MovieDao;

public class InsertMovieAsyncTask extends AsyncTask<Movie,Void,Void> {

    private MovieDao movieDao;

    public InsertMovieAsyncTask(MovieDao movieDao){
        this.movieDao = movieDao;
    }

    @Override
    protected Void doInBackground(Movie... movies) {
        movieDao.insert(movies[0]);
        return null;
    }
}
