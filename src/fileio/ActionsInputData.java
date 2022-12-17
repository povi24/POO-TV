package fileio;

import helpers.LiveInfo;

/**
 * Information about an action, retrieved from parsing the input files
 */
public final class ActionsInputData {
    private  String type;
    private  String page;
    private  String feature;
    private CredentialsInputData credentials;
    private  String startsWith;
    private  FiltersInputData filters;
    private int count;
    private int rate;

    private String movie;

    public String getMovie() {
        return movie;
    }

    public void setMovie(final String movie) {
        this.movie = movie;
    }

    public ActionsInputData() {

    }

    private LiveInfo currentStart;

    public LiveInfo getCurrentStart() {
        return currentStart;
    }

    public void setCurrentStart(final LiveInfo currentStart) {
        this.currentStart = currentStart;
    }

    public String getType() {
        return type;
    }

    public void setType(final String type) {
        this.type = type;
    }

    public String getPage() {
        return page;
    }

    public void setPage(final String page) {
        this.page = page;
    }

    public String getFeature() {
        return feature;
    }

    public void setFeature(final String feature) {
        this.feature = feature;
    }


    public String getStartsWith() {
        return startsWith;
    }

    public void setStartsWith(final String startsWith) {
        this.startsWith = startsWith;
    }



    public int getCount() {
        return count;
    }

    public void setCount(final int count) {
        this.count = count;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(final int rate) {
        this.rate = rate;
    }


    public CredentialsInputData getCredentials() {
        return credentials;
    }

    public void setCredentials(final CredentialsInputData credentials) {
        this.credentials = credentials;
    }

    public FiltersInputData getFilters() {
        return filters;
    }

    public void setFilters(final FiltersInputData filters) {
        this.filters = filters;
    }
}
