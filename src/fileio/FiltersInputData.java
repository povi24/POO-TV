package fileio;

import java.util.ArrayList;

public final class FiltersInputData {
    //avem sort si contains

    private ArrayList<String> actors;
    private ArrayList<String> genre;
    private String rating;
    private String duration;

    public ArrayList<String> getActors() {
        return actors;
    }

    public void setActors(final ArrayList<String> actors) {
        this.actors = actors;
    }

    public ArrayList<String> getGenre() {
        return genre;
    }

    public void setGenre(final ArrayList<String> genre) {
        this.genre = genre;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(final String rating) {
        this.rating = rating;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(final String duration) {
        this.duration = duration;
    }


    public FiltersInputData(final ArrayList<String> actors, final ArrayList<String> genre,
                            final String rating, final String duration) {
        this.actors = actors;
        this.genre = genre;
        this.rating = rating;
        this.duration = duration;
    }


    public FiltersInputData(final FiltersInputData filters) {
        this.actors = filters.getActors();
        this.genre = filters.getGenre();
        this.rating = filters.getRating();
        this.duration = filters.getDuration();
    }
}
