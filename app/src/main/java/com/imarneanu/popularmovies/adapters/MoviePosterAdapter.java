package com.imarneanu.popularmovies.adapters;

import com.imarneanu.popularmovies.R;
import com.imarneanu.popularmovies.data.Movie;
import com.imarneanu.popularmovies.data.NetworkUtils;
import com.squareup.picasso.Picasso;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * Created by imarneanu on 2/8/17.
 */
public class MoviePosterAdapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<String> mMoviePosters;

    public MoviePosterAdapter(Context context) {
        mContext = context;
    }

    public void setMoviePosters(ArrayList<Movie> movies) {
        mMoviePosters = new ArrayList<>(movies.size());
        for (Movie movie : movies) {
            mMoviePosters.add(movie.getPosterPath());
        }
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mMoviePosters == null ? 0 : mMoviePosters.size();
    }

    @Override
    public Object getItem(int i) {
        return mMoviePosters.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ImageView imageView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            imageView = (ImageView) inflater.inflate(R.layout.item_grid, null);
        } else {
            imageView = (ImageView) view;
        }

        Picasso.with(mContext).load(NetworkUtils.buildImageUrlPath(mMoviePosters.get(i))).into(imageView);
        return imageView;
    }
}
