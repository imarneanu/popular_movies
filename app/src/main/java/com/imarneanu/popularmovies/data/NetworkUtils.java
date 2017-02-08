package com.imarneanu.popularmovies.data;

import com.imarneanu.popularmovies.BuildConfig;

import android.net.Uri;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by imarneanu on 2/7/17.
 */
public class NetworkUtils {
    private static final String TAG = NetworkUtils.class.getSimpleName();

    private static final String BASE_URL = "https://api.themoviedb.org/3/";
    private static final String MOVIE_POPULAR = "movie/popular";
    private static final String MOVIE_TOP_RATED = "movie/top_rated";
    private static final String MOVIE = "movie/";
    private static final String PARAM_API_KEY = "api_key";

    private static final String IMAGE_BASE_URL = "http://image.tmdb.org/t/p/";
    private static final String IMAGE_SIZE = "w185/";

    public enum UrlType {POPULAR, TOP_RATED, MOVIE_DETAILS}

    public static URL buildUrl(UrlType type) {
        return buildUrl(type, null);
    }

    public static URL buildUrl(UrlType type, String id) {
        try {
            switch (type) {
                case POPULAR:
                    return new URL(buildPopularUri().toString());
                case TOP_RATED:
                    return new URL(buildTopRatedUri().toString());
                case MOVIE_DETAILS:
                    return new URL(buildMovieDetailsUri(id).toString());
            }
        } catch (MalformedURLException e) {
            Log.e(TAG, e.getMessage(), e);
        }
        return null;
    }

    private static Uri buildPopularUri() {
        return Uri.parse(BASE_URL).buildUpon().appendEncodedPath(MOVIE_POPULAR)
                .appendQueryParameter(PARAM_API_KEY, BuildConfig.API_KEY).build();
    }

    private static Uri buildTopRatedUri() {
        return Uri.parse(BASE_URL).buildUpon().appendPath(MOVIE_TOP_RATED)
                .appendQueryParameter(PARAM_API_KEY, BuildConfig.API_KEY).build();
    }

    private static Uri buildMovieDetailsUri(String movieId) {
        return Uri.parse(BASE_URL).buildUpon().appendEncodedPath(MOVIE).appendEncodedPath(movieId)
                .appendQueryParameter(PARAM_API_KEY, BuildConfig.API_KEY).build();
    }

    public static String buildImageUrlPath(String thumbnail) {
        return IMAGE_BASE_URL.concat(IMAGE_SIZE).concat(thumbnail);
    }

    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();
            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            }
            return null;
        } finally {
            urlConnection.disconnect();
        }
    }
}
