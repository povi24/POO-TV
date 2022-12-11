package fileio;

/**
 * Information about an action, retrieved from parsing the input files
 */
public final class ActionsInputData {
    private  String type;
    private  String page;
    private  String feature;
    private  String credentials; //fac o clasa aici
    private  String startsWith;
    private  String filters; //fac o clasa pentru asta
    private int count;
    private int rate;

    public ActionsInputData(final String type, final String page, final String feature,
                            final String credentials, final String startsWith,
                            final String filters, final int count, final int rate) {
        this.type = type;
        this.page = page;
        this.feature = feature;
        this.credentials = credentials;
        this.startsWith = startsWith;
        this.filters = filters;
        this.count = count;
        this.rate = rate;
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

    public String getCredentials() {
        return credentials;
    }

    public void setCredentials(final String credentials) {
        this.credentials = credentials;
    }

    public String getStartsWith() {
        return startsWith;
    }

    public void setStartsWith(final String startsWith) {
        this.startsWith = startsWith;
    }

    public String getFilters() {
        return filters;
    }

    public void setFilters(final String filters) {
        this.filters = filters;
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
}
