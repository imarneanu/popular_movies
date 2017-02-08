package com.imarneanu.popularmovies.data;

/**
 * Created by imarneanu on 2/8/17.
 */
public class Movie {

    private String posterPath;

    public Movie(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getPosterPath() {
        return posterPath;
    }

}
