package Pages;

public class Movies extends Page{
    public Movies() {
        if(getPageType().equals("movies")) {
            this.setPageType("movies");
            this.getAllowedPages().add("HomePage");
            this.getAllowedPages().add("logout");
            this.getAllowedPages().add("see details");

        }
    }
}
