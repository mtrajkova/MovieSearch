package com.labs.mpip.lab2.persistance;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.labs.mpip.lab2.models.Movie;
import com.labs.mpip.lab2.persistance.dao.MovieDao;

@Database(entities = {Movie.class}, version = 1, exportSchema = false)
public abstract class MovieDatabase extends RoomDatabase {

    public abstract MovieDao movieDao();

    private static MovieDatabase movieDatabase;

    public static MovieDatabase getMovieDatabase(Context context) {
        if (movieDatabase == null) {
            synchronized (MovieDatabase.class) {
                if (movieDatabase == null)
                    movieDatabase = Room.databaseBuilder(context.getApplicationContext(), MovieDatabase.class, "movie_database").build();
            }
        }
        return movieDatabase;
    }
}
