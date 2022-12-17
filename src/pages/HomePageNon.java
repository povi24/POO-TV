package pages;

/**
 * Homepage where a user is not authenticated
 */
public final  class HomePageNon extends Page {
    private static HomePageNon instance = null;

    /**
     * @return instance
     */
    public static HomePageNon getInstance() {
        if (instance == null) {
            instance = new HomePageNon();

        }
        return instance;
    }
    private HomePageNon() {
        this.setPageType("HomePageNon");
        this.getAllowedPages().add("register");
        this.getAllowedPages().add("login");
    }
}
