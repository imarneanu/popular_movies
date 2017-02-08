package com.imarneanu.popularmovies.data;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import java.util.ArrayList;

/**
 * Created by imarneanu on 2/8/17.
 */
public class JsonUtils {
    private static final String TAG = JsonUtils.class.getSimpleName();

    public static ArrayList<Movie> parseJsonMovie(String jsonString) {
        try {
            JSONObject response = new JSONObject(jsonString);
            JSONArray results = response.getJSONArray("results");
            ArrayList<Movie> movies = new ArrayList<>(results.length());
            for (int index = 0; index < results.length(); index++) {
                JSONObject jsonMovie = results.getJSONObject(index);
                String posterPath = jsonMovie.getString("poster_path");
                movies.add(new Movie(posterPath));
            }
            return movies;
        } catch (JSONException e) {
            Log.e(TAG, e.getMessage(), e);
        }
        return null;
    }

}
