package fileio;


import java.util.ArrayList;


/**
 * Information about an user, retrieved from parsing the input files
 */
public final class UsersInputData {
    private  CredentialsInputData credentials;

    private ArrayList<MoviesInputData> purchasedMovies = new ArrayList<>();
    private ArrayList<MoviesInputData>  watchedMovies = new ArrayList<>();
    private ArrayList<MoviesInputData>  likedMovies = new ArrayList<>();
    private ArrayList<MoviesInputData>  ratedMovies = new ArrayList<>();
    private int tokens = 0;
    private static final int NumberOfFreePremiumMovies = 15;
    private int numFreePremiumMovies = NumberOfFreePremiumMovies;

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

    public int getTokens() {
        return tokens;
    }

    public void setTokens(final int tokens) {
        this.tokens = tokens;
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
                          final int tokens, final int numFreePremiumMovies) {
        this.credentials = credentials;
        this.purchasedMovies = purchasedMovies;
        this.watchedMovies = watchedMovies;
        this.likedMovies = likedMovies;
        this.ratedMovies = ratedMovies;
        this.tokens = tokens;
        this.numFreePremiumMovies = numFreePremiumMovies;
    }


    public UsersInputData(final UsersInputData users) {
        this.credentials = new CredentialsInputData(users.getCredentials());

        this.purchasedMovies = new ArrayList<>();
        for (MoviesInputData movies : users.getPurchasedMovies()) {
            this.purchasedMovies.add(new MoviesInputData(movies));
        }

        this.watchedMovies = new ArrayList<>();
        for (MoviesInputData movies : users.getWatchedMovies()) {
            this.watchedMovies.add(new MoviesInputData(movies));
        }

        this.likedMovies = new ArrayList<>();
        for (MoviesInputData movies : users.getLikedMovies()) {
            this.likedMovies.add(new MoviesInputData(movies));
        }

        this.ratedMovies = new ArrayList<>();
        for (MoviesInputData movies : users.getRatedMovies()) {
            this.ratedMovies.add(new MoviesInputData(movies));
        }

        this.tokens = users.getTokens();
        this.numFreePremiumMovies = users.getNumFreePremiumMovies();

    }
}
