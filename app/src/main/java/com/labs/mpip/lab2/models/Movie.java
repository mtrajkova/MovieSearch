package com.labs.mpip.lab2.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "movies")
public class Movie {

    @PrimaryKey
    @NonNull
    public String imdbID;

    @SerializedName("Title")
    @ColumnInfo(name = "title")
    public String title;

    public Movie(@NonNull String mId, String title) {
        this.imdbID = mId;
        this.title = title;
    }

    public Movie(){}

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @NonNull
    public String getImdbID() {
        return imdbID;
    }

    public void setImdbID(@NonNull String imdbID) {
        this.imdbID = imdbID;
    }
}
