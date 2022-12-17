package fileio;


import helpers.Constants;

import java.util.ArrayList;


/**
 * Information about an user, retrieved from parsing the input files
 */
public final class UsersInputData {
    private  CredentialsInputData credentials;

    private int tokensCount = 0;
    private int numFreePremiumMovies = Constants.NUM_OF_FREE_PREMIUM_MOVIES;
    private ArrayList<MoviesInputData> purchasedMovies = new ArrayList<>();
    private ArrayList<MoviesInputData>  watchedMovies = new ArrayList<>();
    private ArrayList<MoviesInputData>  likedMovies = new ArrayList<>();
    private ArrayList<MoviesInputData>  ratedMovies = new ArrayList<>();

//    private int numFreePremiumMovies = NumberOfFreePremiumMovies;

    public UsersInputData() {

    }
    public UsersInputData(CredentialsInputData credentials) {
        this.credentials = new CredentialsInputData(credentials);
    }

    public CredentialsInputData getCredentials() {
        return credentials;
    }

    public void setCredentials(final CredentialsInputData credentials) {
        this.credentials = credentials;
    }

    public ArrayList<MoviesInputData> getPurchasedMovies() {
        return purchasedMovies;
    }

    public void setPurchasedMovies(final ArrayList<MoviesInputData> purchasedMovies) {
        this.purchasedMovies = purchasedMovies;
    }

    public ArrayList<MoviesInputData> getWatchedMovies() {
        return watchedMovies;
    }

    public void setWatchedMovies(final ArrayList<MoviesInputData> watchedMovies) {
        this.watchedMovies = watchedMovies;
    }

    public ArrayList<MoviesInputData> getLikedMovies() {
        return likedMovies;
    }

    public void setLikedMovies(final ArrayList<MoviesInputData> likedMovies) {
        this.likedMovies = likedMovies;
    }

    public ArrayList<MoviesInputData> getRatedMovies() {
        return ratedMovies;
    }

    public void setRatedMovies(final ArrayList<MoviesInputData> ratedMovies) {
        this.ratedMovies = ratedMovies;
    }

    public int getTokensCount() {
        return tokensCount;
    }

    public void setTokensCount(final int tokensCount) {
        this.tokensCount = tokensCount;
    }

    public int getNumFreePremiumMovies() {
        return numFreePremiumMovies;
    }

    public void setNumFreePremiumMovies(final int numFreePremiumMovies) {
        this.numFreePremiumMovies = numFreePremiumMovies;
    }


    public UsersInputData(final CredentialsInputData credentials,
                          final  ArrayList<MoviesInputData> purchasedMovies,
                          final ArrayList<MoviesInputData> watchedMovies,
                          final ArrayList<MoviesInputData> likedMovies,
                          final ArrayList<MoviesInputData> ratedMovies,
                          final int tokensCount, final int numFreePremiumMovies) {
        this.credentials = credentials;
        this.purchasedMovies = purchasedMovies;
        this.watchedMovies = watchedMovies;
        this.likedMovies = likedMovies;
        this.ratedMovies = ratedMovies;
        this.tokensCount = tokensCount;
        this.numFreePremiumMovies = numFreePremiumMovies;
    }


    public UsersInputData(final UsersInputData user) {
        this.credentials = new CredentialsInputData(user.getCredentials());

        this.purchasedMovies = new ArrayList<>();
        for (MoviesInputData movies : user.getPurchasedMovies()) {
            this.purchasedMovies.add(new MoviesInputData(movies));
        }

        this.watchedMovies = new ArrayList<>();
        for (MoviesInputData movies : user.getWatchedMovies()) {
            this.watchedMovies.add(new MoviesInputData(movies));
        }

        this.likedMovies = new ArrayList<>();
        for (MoviesInputData movies : user.getLikedMovies()) {
            this.likedMovies.add(new MoviesInputData(movies));
        }

        this.ratedMovies = new ArrayList<>();
        for (MoviesInputData movies : user.getRatedMovies()) {
            this.ratedMovies.add(new MoviesInputData(movies));
        }

        this.tokensCount = user.getTokensCount();
        this.numFreePremiumMovies = user.getNumFreePremiumMovies();
    }

//    public  UsersInputData(final UsersInputData users) {
//        this.credentials = users.getCredentials();
//        this.purchasedMovies = users.getPurchasedMovies();
//        this.watchedMovies = users.watchedMovies;
//        this.likedMovies = users.likedMovies;
//        this.ratedMovies = users.getRatedMovies();
//        this.tokens = users.getTokens();
//        this.numFreePremiumMovies = users.getNumFreePremiumMovies();
//    }

}
