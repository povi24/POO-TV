package Pages;

/**
 * Homepage where a user is not authenticated
 */
public class HomePageNon extends Page {
    public HomePageNon() {
        if(getPageType().equals("HomePageNon")) {
            this.setPageType("HomePage");
            this.getAllowedPages().add("register");
            this.getAllowedPages().add("login");
        }
    }

}
