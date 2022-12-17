package Pages;

/**
 * Homepage where a user is not authenticated
 */
public class HomePageNon extends Page {
    private static HomePageNon instance = null;

    public static HomePageNon getInstance() {
        if(instance == null) {
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
