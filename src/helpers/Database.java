package helpers;

import fileio.MoviesInputData;
import fileio.UsersInputData;

import java.util.ArrayList;

public final class Database {
    private final ArrayList<UsersInputData> databaseUsers;
    private final ArrayList<MoviesInputData> databaseMovies;
    private static Database instance = null;

    /**
     * @return instance
     */
    public static Database getInstance() {
        if (instance == null) {
            instance = new Database();

        }
        return instance;
    }

    /**
     *Our Database of Movies and Users
     */
    private Database() {
        databaseMovies = new ArrayList<>();
        databaseUsers = new ArrayList<>();
    }

    public ArrayList<UsersInputData> getDatabaseUsers() {
        return databaseUsers;
    }

    public ArrayList<MoviesInputData> getDatabaseMovies() {
        return databaseMovies;
    }

    /**
     * Here we reset the Database
     */
    public void resetDatabase() {
        databaseUsers.clear();
        databaseMovies.clear();
    }
}
