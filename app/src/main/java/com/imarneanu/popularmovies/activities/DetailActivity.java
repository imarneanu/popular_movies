package com.imarneanu.popularmovies.activities;

import com.imarneanu.popularmovies.R;
import com.imarneanu.popularmovies.data.Movie;
import com.imarneanu.popularmovies.data.NetworkUtils;
import com.squareup.picasso.Picasso;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by imarneanu on 2/8/17.
 */
public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_MOVIE = "movie";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        setTitle(R.string.activity_detail);

        Movie movie = getIntent().getParcelableExtra(EXTRA_MOVIE);
        if (movie == null) {
            return;
        }
        setData(movie);
    }

    private void setData(Movie movie) {
        TextView title = (TextView) findViewById(R.id.details_original_title);
        title.setText(movie.getOriginalTitle());
        ImageView poster = (ImageView) findViewById(R.id.details_poster);
        Picasso.with(this).load(NetworkUtils.buildImageUrlPath(movie.getPosterPath())).into(poster);
        TextView releaseDate = (TextView) findViewById(R.id.details_release_date);
        releaseDate.setText(movie.getReleaseDate());
        TextView userRating = (TextView) findViewById(R.id.details_user_rating);
        userRating.setText(movie.getUserRating());
        TextView plot = (TextView) findViewById(R.id.details_plot);
        plot.setText(movie.getPlot());
    }
}
