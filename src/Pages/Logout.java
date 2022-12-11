package Pages;

public class Logout extends Page{
    public Logout() {
        if(getPageType().equals("logout")) {
            this.setPageType("logout");
            this.getAllowedPages().add("HomePageNon");
        }
    }
}
