package com.imarneanu.popularmovies;

import com.imarneanu.popularmovies.data.NetworkUtils;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

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
    }
}
