package com.imarneanu.popularmovies;

import com.imarneanu.popularmovies.adapters.MoviePosterAdapter;
import com.imarneanu.popularmovies.data.JsonUtils;
import com.imarneanu.popularmovies.data.Movie;
import com.imarneanu.popularmovies.data.NetworkUtils;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.GridView;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private final static String TAG = MainActivity.class.getSimpleName();

    private MoviePosterAdapter mMoviePosterAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMoviePosterAdapter = new MoviePosterAdapter(this);
        GridView mMoviesGridView = (GridView) findViewById(R.id.movies_gridview);
        mMoviesGridView.setAdapter(mMoviePosterAdapter);

        new MovieDbQueryTask().execute(NetworkUtils.buildUrl(NetworkUtils.UrlType.POPULAR));
    }

    private void loadPopularMovies(ArrayList<Movie> movies) {
        mMoviePosterAdapter.setMoviePosters(movies);
    }

    private void showErrorMessage() {
        findViewById(R.id.movies_error_message).setVisibility(View.VISIBLE);
    }

    public class MovieDbQueryTask extends AsyncTask<URL, Void, String> {

        @Override
        protected String doInBackground(URL... urls) {
            URL url = urls[0];
            String responseString = null;
            try {
                responseString = NetworkUtils.getResponseFromHttpUrl(url);
            } catch (IOException e) {
                Log.e(TAG, e.getMessage(), e);
            }
            return responseString;
        }

        @Override
        protected void onPostExecute(String s) {
            if (TextUtils.isEmpty(s)) {
                showErrorMessage();
                return;
            }
            loadPopularMovies(JsonUtils.parseJsonMovie(s));
        }
    }
}
