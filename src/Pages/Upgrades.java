package Pages;

public class Upgrades extends Page{
    public Upgrades() {
        if(getPageType().equals("upgrades")) {
            this.setPageType("upgrades");
            this.getAllowedPages().add("HomePage");
            this.getAllowedPages().add("logout");
            this.getAllowedPages().add("movies");
        }
    }
}
