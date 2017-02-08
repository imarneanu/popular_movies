package com.imarneanu.popularmovies.data;

/**
 * Created by imarneanu on 2/8/17.
 */
public class Movie {

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
