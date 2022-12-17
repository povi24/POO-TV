package Pages;

public class Upgrades extends Page{

    private static Upgrades instance = null;

    public static Upgrades getInstance() {
        if(instance == null) {
            instance = new Upgrades();

        }
        return instance;
    }
    private Upgrades() {
        this.setPageType("upgrades");
        this.getAllowedPages().add("HomePage");
        this.getAllowedPages().add("logout");
        this.getAllowedPages().add("movies");
    }



}
