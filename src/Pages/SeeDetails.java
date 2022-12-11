package Pages;

public class SeeDetails extends Page{
    public SeeDetails() {
        if(getPageType().equals("see details")) {
            this.setPageType("see details");
            this.getAllowedPages().add("HomePage");
            this.getAllowedPages().add("upgrades");
            this.getAllowedPages().add("logout");
            this.getAllowedPages().add("movies");

        }
    }
}
