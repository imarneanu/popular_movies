package com.imarneanu.popularmovies;

import com.imarneanu.popularmovies.data.Movie;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

/**
 * Created by imarneanu on 2/8/17.
 */
public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_MOVIE = "movie";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        if (getIntent().getParcelableExtra(EXTRA_MOVIE) != null) {
            Movie movie = getIntent().getParcelableExtra(EXTRA_MOVIE);
            Log.d(EXTRA_MOVIE, movie.getOriginalTitle());
        }

    }
}
