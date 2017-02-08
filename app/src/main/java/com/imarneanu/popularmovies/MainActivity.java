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
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private final static String TAG = MainActivity.class.getSimpleName();

    private MoviePosterAdapter mMoviePosterAdapter;
    private boolean mShowPopular;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMoviePosterAdapter = new MoviePosterAdapter(this);
        GridView moviesGridView = (GridView) findViewById(R.id.movies_gridview);
        moviesGridView.setAdapter(mMoviePosterAdapter);
        moviesGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                Toast.makeText(getApplicationContext(), "POSITION: " + position, Toast.LENGTH_SHORT).show();

            }
        });

        mShowPopular = true;
        new MovieDbQueryTask().execute(NetworkUtils.buildUrl(NetworkUtils.UrlType.POPULAR));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.movie, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_filter_popular) {
            if (!mShowPopular) {
                new MovieDbQueryTask().execute(NetworkUtils.buildUrl(NetworkUtils.UrlType.POPULAR));
            }
            mShowPopular = true;
            return true;
        }

        if (id == R.id.action_filter_top_rated) {
            if (mShowPopular) {
                new MovieDbQueryTask().execute(NetworkUtils.buildUrl(NetworkUtils.UrlType.TOP_RATED));
            }
            mShowPopular = false;
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void loadMovies(ArrayList<Movie> movies) {
        mMoviePosterAdapter.setMoviePosters(movies);
    }

    private void showErrorMessage() {
        findViewById(R.id.movies_error_message).setVisibility(View.VISIBLE);
    }

    private void showLoading(boolean show) {
        findViewById(R.id.movies_progressbar).setVisibility(show ? View.VISIBLE : View.GONE);
    }

    public class MovieDbQueryTask extends AsyncTask<URL, Void, String> {

        @Override
        protected void onPreExecute() {
            showLoading(true);
        }

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
            showLoading(false);
            if (TextUtils.isEmpty(s)) {
                showErrorMessage();
                return;
            }
            loadMovies(JsonUtils.parseJsonMovie(s));
        }
    }
}
