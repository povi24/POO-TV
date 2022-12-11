package Pages;

public class HomePage extends Page{
    public HomePage() {
        if(getPageType().equals("HomePage")) {
            this.setPageType("HomePage");
            this.getAllowedPages().add("movies");
            this.getAllowedPages().add("logout");
            this.getAllowedPages().add("upgrades");
        }
    }

}
