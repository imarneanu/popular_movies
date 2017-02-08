package com.imarneanu.popularmovies;

import com.imarneanu.popularmovies.data.NetworkUtils;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;

import java.io.IOException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private final static String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        URL url = NetworkUtils.buildUrl(NetworkUtils.UrlType.POPULAR);
        Log.d(TAG, "URL: " + url);
        url = NetworkUtils.buildUrl(NetworkUtils.UrlType.TOP_RATED);
        Log.d(TAG, "URL: " + url);
        url = NetworkUtils.buildUrl(NetworkUtils.UrlType.MOVIE_DETAILS, "328111");
        Log.d(TAG, "URL: " + url);

        new MovieDbQueryTask().execute(NetworkUtils.buildUrl(NetworkUtils.UrlType.POPULAR));
    }

    private void loadPopularMovies(String s) {

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
                return;
            }
            loadPopularMovies(s);
        }
    }
}
