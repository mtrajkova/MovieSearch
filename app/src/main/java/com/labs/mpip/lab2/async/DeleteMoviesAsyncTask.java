package com.labs.mpip.lab2.async;

import android.os.AsyncTask;

import com.labs.mpip.lab2.persistance.dao.MovieDao;

public class DeleteMoviesAsyncTask extends AsyncTask<Void,Void,Void> {
    private MovieDao movieDao;

    public DeleteMoviesAsyncTask(MovieDao movieDao){
        this.movieDao = movieDao;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        movieDao.deleteAll();
        return null;
    }
}
