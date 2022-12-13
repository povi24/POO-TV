package fileio;

import java.util.ArrayList;

public final class ContainsInputData {
    private ArrayList<String> actors;
    private ArrayList<String> genre;

    public ContainsInputData() {

    }

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
}
