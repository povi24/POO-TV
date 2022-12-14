package helpers;

import Pages.HomePageNon;
import Pages.Page;
import Pages.Register;
import Pages.VerifyType;
import fileio.MoviesInputData;
import fileio.UsersInputData;

import java.util.ArrayList;

/**
 * Here we initialize the application for a user
 */
public final class LiveInfo {
    private UsersInputData currentUser;
    private ArrayList<MoviesInputData> currentMovieList;
    private Page currentPage;

    private static LiveInfo instance = null;

    public static LiveInfo getInstance() {
        if(instance == null) {
            instance = new LiveInfo();

        }
        return instance;
    }

    public void initializeApp() {
        currentUser = null;
        currentMovieList = new ArrayList<>();
        currentPage = HomePageNon.getInstance();
    }

    public UsersInputData getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(final UsersInputData currentUser) {
        this.currentUser = currentUser;
    }

    public ArrayList<MoviesInputData> getCurrentMovieList() {
        return currentMovieList;
    }

    public void setCurrentMovieList(final ArrayList<MoviesInputData> currentMovieList) {
        this.currentMovieList = currentMovieList;
    }

    public Page getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(final Page currentPage) {
        this.currentPage = currentPage;
    }
}
