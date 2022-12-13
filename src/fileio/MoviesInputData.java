package fileio;

import java.util.ArrayList;

/**
 * Information about a movie, retrieved from parsing the input files
 */
public final class MoviesInputData {
    private String name;
    private int year;
    private int duration;
    private ArrayList<String> genres;
    private ArrayList<String> actors;
    private ArrayList<String> countriesBanned;
    private int numLikes;
    private int numRatings;
    private int rating;

    public MoviesInputData() {

    }
    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(final int year) {
        this.year = year;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(final int duration) {
        this.duration = duration;
    }

    public ArrayList<String> getGenres() {
        return genres;
    }

    public void setGenres(final ArrayList<String> genres) {
        this.genres = genres;
    }

    public ArrayList<String> getActors() {
        return actors;
    }

    public void setActors(final ArrayList<String> actors) {
        this.actors = actors;
    }

    public ArrayList<String> getCountriesBanned() {
        return countriesBanned;
    }

    public void setCountriesBanned(final ArrayList<String> countriesBanned) {
        this.countriesBanned = countriesBanned;
    }

    public int getNumLikes() {
        return numLikes;
    }

    public void setNumLikes(final int numLikes) {
        this.numLikes = numLikes;
    }

    public int getNumRatings() {
        return numRatings;
    }

    public void setNumRatings(final int numRatings) {
        this.numRatings = numRatings;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(final int rating) {
        this.rating = rating;
    }


    public MoviesInputData(final String name, final int year, final int duration,
                           final ArrayList<String> genres, final ArrayList<String> actors,
                           final ArrayList<String> countriesBanned,
                           int numLikes, int numRatings, int rating) {
        this.name = name;
        this.year = year;
        this.duration = duration;
        this.genres = genres;
        this.actors = actors;
        this.countriesBanned = countriesBanned;
        this.numLikes = numLikes;
        this.numRatings = numRatings;
        this.rating = rating;
    }

    public MoviesInputData(final MoviesInputData movies) {
        this.name = movies.getName();
        this.year = movies.getYear();
        this.duration = movies.getDuration();
        this.genres = movies.getGenres();
        this.actors = movies.getActors();
        this.countriesBanned = movies.getCountriesBanned();
        this.numLikes = movies.getNumLikes();
        this.numRatings = movies.getNumRatings();
        this.rating = movies.getRating();
    }


    @Override
    public String toString() {
        return "MoviesInputData{"
                + "name'" + name + '\''
                + ", year=" + year
                + ", duration=" + duration
                + ", actors=" + actors
                + ", genres" +genres
                + '}';
    }
}
