package fileio;

import java.util.ArrayList;

public final class Input {
    /**
     * This class retrieves information from our input
     */

    private  ArrayList<UsersInputData> users;
    private  ArrayList<MoviesInputData> movies;
    private  ArrayList<ActionsInputData> actions;

    public ArrayList<UsersInputData> getUsers() {
        return users;
    }

    public void setUsers(final ArrayList<UsersInputData> users) {
        this.users = users;
    }

    public ArrayList<MoviesInputData> getMovies() {
        return movies;
    }

    public void setMovies(final ArrayList<MoviesInputData> movies) {
        this.movies = movies;
    }

    public ArrayList<ActionsInputData> getActions() {
        return actions;
    }

    public void setActions(final ArrayList<ActionsInputData> actions) {
        this.actions = actions;
    }
}
