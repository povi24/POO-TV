package pages;

public final class Movies extends Page {

    private static Movies instance = null;

    /**
     * @return instance
     */
    public static Movies getInstance() {
        if (instance == null) {
            instance = new Movies();

        }
        return instance;
    }
    private Movies() {
        this.setPageType("movies");
        this.getAllowedPages().add("HomePage");
        this.getAllowedPages().add("logout");
        this.getAllowedPages().add("see details");
        this.getAllowedPages().add("upgrades");
        this.getAllowedPages().add("movies");
    }


}
