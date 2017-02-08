package com.imarneanu.popularmovies.data;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by imarneanu on 2/8/17.
 */
public class Movie implements Parcelable {

    private String originalTitle;
    private String posterPath;
    private String plot;
    private String userRating;
    private String releaseDate;

    private Movie(String originalTitle, String posterPath, String plot, String userRating, String
            releaseDate) {
        this.originalTitle = originalTitle;
        this.posterPath = posterPath;
        this.plot = plot;
        this.userRating = userRating;
        this.releaseDate = releaseDate;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public String getPlot() {
        return plot;
    }

    public String getUserRating() {
        return userRating;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.originalTitle);
        parcel.writeString(this.posterPath);
        parcel.writeString(this.plot);
        parcel.writeString(this.userRating);
        parcel.writeString(this.releaseDate);
    }

    private Movie(Parcel in) {
        this.originalTitle = in.readString();
        this.posterPath = in.readString();
        this.plot = in.readString();
        this.userRating = in.readString();
        this.releaseDate = in.readString();
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        public Movie createFromParcel(Parcel source) {
            return new Movie(source);
        }

        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    public static class MovieBuilder {
        private String originalTitle;
        private String posterPath;
        private String plot;
        private String userRating;
        private String releaseDate;

        public MovieBuilder(String posterPath) {
            this.posterPath = posterPath;
        }

        public MovieBuilder originalTitle(String originalTitle) {
            this.originalTitle = originalTitle;
            return this;
        }

        public MovieBuilder plot(String plot) {
            this.plot = plot;
            return this;
        }

        public MovieBuilder userRating(String userRating) {
            this.userRating = userRating;
            return this;
        }

        public MovieBuilder releaseDate(String releaseDate) {
            this.releaseDate = releaseDate;
            return this;
        }

        public Movie createMovie() {
            return new Movie(originalTitle, posterPath, plot, userRating, releaseDate);
        }
    }

}
